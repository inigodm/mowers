package com.seatcode.mowers

import assertk.assertThat
import org.junit.jupiter.api.Test

class InputValidationTests {
    @Test
    fun `input must contain a first line which is the size of the ground`() {
        val input = """5 6
            1 2 N
            LLLMRM
        """.trimMargin()
        val res =  InputParser().parse(input)
        assertThat(res.mapSizeX).equals(5)
        assertThat(res.mapSizeX).equals(6)
    }
}