package com.seatcode.mowers

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.seatcode.mowers.infrastructure.InputParser
import com.seatcode.mowers.infrastructure.InvalidInputError
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class InputValidationTests {
    @Test
    fun `input must contain a first line which is the size of the plateau`() {
        val input = """5 6
            1 2 N
            LLLMRM
        """.trimMargin()
        val res =  InputParser().parse(input)
        assertThat(res.plateauSizeX).isEqualTo(5)
        assertThat(res.plateauSizeY).isEqualTo(6)
    }

    @Test
    fun `Should fail if first line is not a plateau size`() {
        val input = """1 2 N
            LLLMRM
        """.trimMargin()
        val e = assertThrows<InvalidInputError> { InputParser().parse(input) }
        // I want this exact message in my logs if is an execution error
        assertThat(e.message).isEqualTo("""First line of input should be a map size. p.e.: '5 5', actual value: 1 2 N
            LLLMRM""")

    }

    @Test
    fun `should fail if plateau size is void`() {
        val input = """
            LLLMRM
        """.trimMargin()
        val e = assertThrows<InvalidInputError> { InputParser().parse(input) }
        assertThat(e.message).isEqualTo("""First line of input should be a map size. p.e.: '5 5', actual value: LLLMRM""".trimMargin())
    }

    @Test
    fun `should accept multiple whitespaces wrapping plateau size`() {
        val input = """                                 5 6                          
            1 2 N
            LLLMRM
        """.trimMargin()
        val res =  InputParser().parse(input)
        assertThat(res.plateauSizeX).isEqualTo(5)
        assertThat(res.plateauSizeY).isEqualTo(6)
    }

    @Test
    fun `second line should be robot initial state`() {
        val input = """5 6
            1 2 N
            LLLMRM
        """.trimMargin()
        val res =  InputParser().parse(input)
        assertThat(res.robotX).isEqualTo(1)
        assertThat(res.robotY).isEqualTo(2)
        assertThat(res.robotOrientation).isEqualTo("N")
    }

    @Test
    fun `should accept multiple whitespaces wrapping robot status`() {
        //TODO
    }

    @Test
    fun `should fail if robot status is void`() {
        val input = """5 6
           
            LLLMRM
        """.trimMargin()
        val e = assertThrows<InvalidInputError> { InputParser().parse(input) }
        // I want this exact message in my logs if is an execution error
        assertThat(e.message).isEqualTo("""Second line of input should be a robot initial state. p.e.: '1 2 N', actual value: """)
    }

    @Test
    fun `should fail if robot status is not a valid orientation`() {
        val input = """5 6
            1 2 X
            LLLMRM
        """.trimMargin()
        val e = assertThrows<InvalidInputError> { InputParser().parse(input) }
        // I want this exact message in my logs if is an execution error
        assertThat(e.message).isEqualTo("""Second line of input should be a robot initial state. p.e.: '1 2 N', actual value: 1 2 X""")
    }

    @Test
    fun `should fail if robot status is not a valid position`() {
        val input = """5 6
            1 X N
            LLLMRM
        """.trimMargin()
        val e = assertThrows<InvalidInputError> { InputParser().parse(input) }
        // I want this exact message in my logs if is an execution error
        assertThat(e.message).isEqualTo("""Second line of input should be a robot initial state. p.e.: '1 2 N', actual value: 1 X N""")
    }
}