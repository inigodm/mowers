package com.seatcode.mowers

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.seatcode.mowers.application.CalculateFinalPositions
import com.seatcode.mowers.domain.robot.Position
import com.seatcode.mowers.domain.vo.Direction
import com.seatcode.mowers.domain.vo.X
import com.seatcode.mowers.domain.vo.Y
import com.seatcode.mowers.infrastructure.MowerManager
import com.seatcode.mowers.infrastructure.parser.InputParser
import com.seatcode.mowers.infrastructure.MowersCLI
import io.mockk.mockkObject
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.boot.autoconfigure.condition.ConditionOutcome.match
import kotlin.test.BeforeTest

class MowersApplicationTests {

	lateinit var inputParser: InputParser
	lateinit var mowersCLI: MowersCLI
	lateinit var calculateFinalPositions: CalculateFinalPositions
	lateinit var mowersAdapter: MowerManager //Uso el real, en cualquier caso mockeare la consola interna que tiene...

	@BeforeTest
	fun setUp() {
		inputParser = InputParser()
		mowersAdapter = MowerManager()
		calculateFinalPositions = CalculateFinalPositions(mowersAdapter)
		mowersCLI = MowersCLI(calculateFinalPositions, inputParser)
	}

	@Test
	fun `should respond to a valid input`() {

		mockkObject(MowerManager.ConsoleMowler)
		val input = """		
			5 5
			1 2 N
			LR
		""".trimIndent()
		val expectedOutput = listOf(Position(X.of(1), Y.of(2), Direction.NORTH))

		val output = mowersCLI.runKata(input)

		assertThat(output).isEqualTo(expectedOutput)
		verify(exactly = 1) {
			MowerManager.ConsoleMowler.printLine(match { it.startsWith("Moving robot with id: ") && it.contains(", doing LEFT") })
			MowerManager.ConsoleMowler.printLine(match { it.startsWith("Moving robot with id: ") && it.contains(", doing RIGHT") })
		}
	}


	@Test
	fun `should rotate left`() {
		val input = """		
			5 5
			1 2 N
			L
		""".trimIndent()
		val expectedOutput = listOf(Position(X.of(1), Y.of(2), Direction.WEST))


		val output = mowersCLI.runKata(input)

		assertThat(output).isEqualTo(expectedOutput)
	}

	@Test
	fun `should rotate right`() {
		val input = """		
			5 5
			1 2 N
			R
		""".trimIndent()
		val expectedOutput = listOf(Position(X.of(1), Y.of(2), Direction.EAST))


		val output = mowersCLI.runKata(input)

		assertThat(output).isEqualTo(expectedOutput)
	}

	@Test
	fun `should move north`() {
		val input = """		
			5 5
			1 2 N
			LLLLM
		""".trimIndent()
		val expectedOutput = listOf(Position(X.of(1), Y.of(3), Direction.NORTH))


		val output = mowersCLI.runKata(input)

		assertThat(output).isEqualTo(expectedOutput)
	}

	@Test
	fun `should stop the robot before going outside the plateau`() {
		val input = """		
			5 5
			1 2 N
			LLLLMMMMMMMM
			""".trimIndent()
		val expectedOutput = listOf(Position(X.of(1), Y.of(5), Direction.NORTH))


		val output = mowersCLI.runKata(input)

		assertThat(output).isEqualTo(expectedOutput)
	}

	@Test
	fun `should do nothing if the robot is, at the beginning outside the plateau`() {
		val input = """		
			5 5
			1 7 N
			LLMMMM
		""".trimIndent()
		val expectedOutput = listOf(Position(X.of(1), Y.of(7), Direction.NORTH))


		val output = mowersCLI.runKata(input)

		assertThat(output).isEqualTo(expectedOutput)
	}

	@Test
	fun `plateau can be a file`() {
		val input = """		
			5 0
			1 0 E
			MMM
		""".trimIndent()
		val expectedOutput = listOf(Position(X.of(4), Y.of(0), Direction.EAST))


		val output = mowersCLI.runKata(input)

		assertThat(output).isEqualTo(expectedOutput)
	}

	@Test
	fun `plateau can be a column`() {
		val input = """		
			0 5
			0 1 N
			MMM
		""".trimIndent()
		val expectedOutput = listOf(Position(X.of(0), Y.of(4), Direction.NORTH))


		val output = mowersCLI.runKata(input)

		assertThat(output).isEqualTo(expectedOutput)
	}

	@Test
	fun `should do kata example properly`() {
		mockkObject(MowersCLI.Consola)

		val input = """		
			5 5
			1 2 N
			LMLMLMLMM
			3 3 E
			MMRMMRMRRM
		""".trimIndent()

		mowersCLI.run(input)

		verify(exactly = 1) { MowersCLI.Consola.printLine("1 3 N") }
		verify(exactly = 1) { MowersCLI.Consola.printLine("5 1 E") }
	}
}
