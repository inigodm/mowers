package com.seatcode.mowers.domain.robot

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MovementTest {

    @Test
    fun `fromString should return correct movement`() {
        assertEquals(Movement.MOVE, Movement.fromString("M"))
        assertEquals(Movement.LEFT, Movement.fromString("L"))
        assertEquals(Movement.RIGHT, Movement.fromString("R"))
    }

    @Test
    fun `fromString should throw exception for invalid input`() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            Movement.fromString("INVALID")
        }
        assertEquals("Unknown movement: INVALID", exception.message)
    }
}