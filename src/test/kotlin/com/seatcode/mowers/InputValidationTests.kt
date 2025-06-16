package com.seatcode.mowers

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.seatcode.mowers.infrastructure.parser.InputParser
import com.seatcode.mowers.infrastructure.parser.InvalidInputError
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class InputValidationTests {
    @Test
    fun `input must contain a first line which is the size of the plateau`() {
        val input = """5 6
            1 2 W
            LLLMRM
        """.trimMargin()
        val res = InputParser().parse(input)
        assertThat(res.plateauSizeX).isEqualTo(6)
        assertThat(res.plateauSizeY).isEqualTo(7)
    }

    @Test
    fun `Should fail if first line is not a plateau size`() {
        val input = """1 2 N
            LLLMRM
        """.trimMargin()
        val e = assertThrows<InvalidInputError> { InputParser().parse(input) }
        // I want this exact message in my logs if is an execution error
        assertThat(e.message).isEqualTo("Plateau size input should respect format:" +
                "'<digits><spaces><digits>'. p.e.: '5 5', actual value: 1 2 N")

    }

    @Test
    fun `Should fail if incomplete data reach for a robot`() {
        val input = """
            5 5
            1 2 N
            LLLMRM
            1 2 N""".trimMargin()
        val e = assertThrows<InvalidInputError> { InputParser().parse(input) }
        assertThat(e.message).isEqualTo("Invalid number of lines in input: 3. It should be" +
                " an even number greater than 2, as each robot has two lines of input.")

    }

    @Test
    fun `should fail if plateau size is void`() {
        val input = """
            LLLMRM
        """.trimMargin()
        val e = assertThrows<InvalidInputError> { InputParser().parse(input) }
        assertThat(e.message).isEqualTo(("Plateau size input should respect format:" +
                "'<digits><spaces><digits>'. p.e.: '5 5', actual value: LLLMRM").trimMargin())
    }

    @Test
    fun `should accept multiple whitespaces wrapping plateau size`() {
        val input = """                                 5 6                          
            1 2 S
            LLLMRM
        """.trimMargin()
        val res = InputParser().parse(input)
        assertThat(res.plateauSizeX).isEqualTo(6)
        assertThat(res.plateauSizeY).isEqualTo(7)
    }

    @Test
    fun `second line should be robot initial state`() {
        val input = """5 6
            1 2 N
            LLLMRM
        """.trimMargin()
        val res = InputParser().parse(input)
        assertThat(res.robots[0].x).isEqualTo(1)
        assertThat(res.robots[0].y).isEqualTo(2)
        assertThat(res.robots[0].orientation).isEqualTo("N")
    }

    @Test
    fun `should accept multiple whitespaces wrapping robot status and lowercases`() {
        val input = """   5   6   
                1   2    n    
               lllmrm   
        """.trimMargin()
        val res = InputParser().parse(input)
        assertThat(res.robots[0].x).isEqualTo(1)
        assertThat(res.robots[0].y).isEqualTo(2)
        assertThat(res.robots[0].orientation).isEqualTo("N")
        res.robots[0].statusModifiers.forEachIndexed { index, modifier ->
            when (index) {
                0 -> assertThat(modifier).isEqualTo("L")
                1 -> assertThat(modifier).isEqualTo("L")
                2 -> assertThat(modifier).isEqualTo("L")
                3 -> assertThat(modifier).isEqualTo("M")
                4 -> assertThat(modifier).isEqualTo("R")
                5 -> assertThat(modifier).isEqualTo("M")
            }
        }
    }

    @Test
    fun `should fail if robot status is void`() {
        val input = """5 6
           
            LLLMRM
        """.trimMargin()
        val e = assertThrows<InvalidInputError> { InputParser().parse(input) }
        // I want this exact message in my logs if is an execution error
        assertThat(e.message).isEqualTo(
            "Robot initial state input should respect format:" +
                    "'<digits><spaces><digits><one of NWSE>'. p.e.: '1 2 N', actual value: ")
    }

    @Test
    fun `should fail if robot status is not a valid orientation`() {
        val input = """5 6
            1 2 X
            LLLMRM
        """.trimMargin()
        val e = assertThrows<InvalidInputError> { InputParser().parse(input) }
        // I want this exact message in my logs if is an execution error
        assertThat(e.message).isEqualTo(
            "Robot initial state input should respect format:" +
                    "'<digits><spaces><digits><one of NWSE>'. p.e.: '1 2 N', actual value: 1 2 X")
    }

    @Test
    fun `should fail if robot status is not a valid position`() {
        val input = """5 6
            1 X N
            LLLMRM
        """.trimMargin()
        val e = assertThrows<InvalidInputError> { InputParser().parse(input) }
        // I want this exact message in my logs if is an execution error
        assertThat(e.message).isEqualTo(
            "Robot initial state input should respect format:" +
                    "'<digits><spaces><digits><one of NWSE>'. p.e.: '1 2 N', actual value: 1 X N")
    }

    @Test
    fun `should parse robot movements`() {
        val input = """5 6
            1 2 N
            LLLMRM
        """.trimMargin()
        val res = InputParser().parse(input)
        res.robots[0].statusModifiers.forEachIndexed { index, modifier ->
            when (index) {
                0 -> assertThat(modifier).isEqualTo("L")
                1 -> assertThat(modifier).isEqualTo("L")
                2 -> assertThat(modifier).isEqualTo("L")
                3 -> assertThat(modifier).isEqualTo("M")
                4 -> assertThat(modifier).isEqualTo("R")
                5 -> assertThat(modifier).isEqualTo("M")
            }
        }
    }

    @Test
    fun `should fail if robot movements are not valid`() {
        val input = """5 6
			1 2 N
			LLLMRMZ
		""".trimMargin()
        val e = assertThrows<InvalidInputError> { InputParser().parse(input) }
        assertThat(e.message).isEqualTo(
            "Robot movements input should respect format:" +
                    "'<any of LRM one or more times>'. p.e.: 'LRMRLRRMMM', actual value: LLLMRMZ")
    }

    @Test
    fun `should fail if robot movements are empty`() {
        val input = """5 6
			1 2 N
			
		""".trimMargin()
        val e = assertThrows<InvalidInputError> { InputParser().parse(input) }
        assertThat(e.message).isEqualTo(
            "Robot movements input should respect format:" +
                    "'<any of LRM one or more times>'. p.e.: 'LRMRLRRMMM', actual value: ")
    }

    @Test
    fun `should allow multiple robots in the same file`() {
        val input = """5 6
            1 2 N
            LLLMRM
            3 4 E
            RMMMLL
        """.trimMargin()
        val res = InputParser().parse(input)
        assertThat(res.robots[0].x).isEqualTo(1)
        assertThat(res.robots[0].y).isEqualTo(2)
        assertThat(res.robots[0].orientation).isEqualTo("N")
        res.robots[0].statusModifiers.forEachIndexed { index, modifier ->
            when (index) {
                0 -> assertThat(modifier).isEqualTo("L")
                1 -> assertThat(modifier).isEqualTo("L")
                2 -> assertThat(modifier).isEqualTo("L")
                3 -> assertThat(modifier).isEqualTo("M")
                4 -> assertThat(modifier).isEqualTo("R")
                5 -> assertThat(modifier).isEqualTo("M")
            }
        }
        assertThat(res.robots[1].x).isEqualTo(3)
        assertThat(res.robots[1].y).isEqualTo(4)
        assertThat(res.robots[1].orientation).isEqualTo("E")
        res.robots[1].statusModifiers.forEachIndexed { index, modifier ->
            when (index) {
                0 -> assertThat(modifier).isEqualTo("R")
                1 -> assertThat(modifier).isEqualTo("M")
                2 -> assertThat(modifier).isEqualTo("M")
                3 -> assertThat(modifier).isEqualTo("M")
                4 -> assertThat(modifier).isEqualTo("L")
                5 -> assertThat(modifier).isEqualTo("L")
            }
        }
    }
}