package com.seatcode.mowers

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.seatcode.mowers.infrastructure.InputParser
import com.seatcode.mowers.infrastructure.InvalidInputError
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class InputValidationTests {
    @Test
    fun `input must contain a first line which is the size of the ground`() {
        val input = """5 6
            1 2 N
            LLLMRM
        """.trimMargin()
        val res =  InputParser().parse(input)
        assertThat(res.plateauSizeX).isEqualTo(5)
        assertThat(res.plateauSizeX).isEqualTo(6)
    }

    @Test
    fun `Should fail if first line is not a map size`() {
        val input = """1 2 N
            LLLMRM
        """.trimMargin()
        val e = assertThrows<InvalidInputError> { InputParser().parse(input) }
        // I want this exact message in my logs if is an execution error
        assertThat(e.message).isEqualTo("""First line of input should be a map size. p.e.: '5 5', actual value: 1 2 N
            LLLMRM""")

    }
}