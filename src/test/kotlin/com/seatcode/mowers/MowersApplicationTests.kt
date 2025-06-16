package com.seatcode.mowers

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.seatcode.mowers.application.CalculateFinalPositions
import com.seatcode.mowers.infrastructure.InputParser
import com.seatcode.mowers.infrastructure.InvalidInputError
import com.seatcode.mowers.infrastructure.MowersCLI
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.Ignore

@SpringBootTest
class MowersApplicationTests {

	@Autowired
	lateinit var mowersCLI: MowersCLI

	@Test
	fun `should respond to a valid input`() {
		val input = """		
			5 5
			1 2 N
			LL
		""".trimIndent()
		val expectedOutput = """1 2 S"""

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
		val expectedOutput = """1 2 W"""

		val output = mowersCLI.runKata(input)

		assertThat(output).isEqualTo(expectedOutput)
	}
}
