package com.seatcode.mowers

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.seatcode.mowers.application.CalculateFinalPositions
import com.seatcode.mowers.domain.Direction
import com.seatcode.mowers.domain.Robot
import com.seatcode.mowers.domain.vo.X
import com.seatcode.mowers.domain.vo.Y
import com.seatcode.mowers.infrastructure.InputParser
import com.seatcode.mowers.infrastructure.MowersCLI
import org.junit.jupiter.api.Test
import kotlin.test.BeforeTest

class MowersApplicationTests {

	lateinit var inputParser: InputParser
	lateinit var mowersCLI: MowersCLI
	lateinit var calculateFinalPositions: CalculateFinalPositions

	@BeforeTest
	fun setUp() {
		inputParser = InputParser()
		calculateFinalPositions = CalculateFinalPositions()
		mowersCLI = MowersCLI(calculateFinalPositions, inputParser)
	}

	@Test
	fun `should respond to a valid input`() {
		val input = """		
			5 5
			1 2 N
			LL
		""".trimIndent()
		val expectedOutput = listOf(Robot.Position(X.of(1), Y.of(2), Direction.SOUTH))

		val output = mowersCLI.runKata(input)

		assertThat(output).isEqualTo(expectedOutput)
	}

	@Test
	fun `should rotate left`() {
		val input = """		
			5 5
			1 2 N
			L
		""".trimIndent()
		val expectedOutput = listOf(Robot.Position(X.of(1), Y.of(2), Direction.WEST))


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
		val expectedOutput = listOf(Robot.Position(X.of(1), Y.of(2), Direction.EAST))


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
		val expectedOutput = listOf(Robot.Position(X.of(1), Y.of(3), Direction.NORTH))


		val output = mowersCLI.runKata(input)

		assertThat(output).isEqualTo(expectedOutput)
	}

	@Test
	fun `should stop the robot before going outside the plateau`() {
		val input = """		
			5 5
			1 2 N
			LLLLMMMMMM
		""".trimIndent()
		val expectedOutput = listOf(Robot.Position(X.of(1), Y.of(5), Direction.NORTH))


		val output = mowersCLI.runKata(input)

		assertThat(output).isEqualTo(expectedOutput)
	}
}
