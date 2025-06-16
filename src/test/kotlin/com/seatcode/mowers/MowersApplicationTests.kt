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
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.Ignore

@SpringBootTest
class MowersApplicationTests {

	lateinit var inputParser: InputParser
	lateinit var mowersCLI: MowersCLI

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
	@Ignore
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
}
