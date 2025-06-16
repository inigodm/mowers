package com.seatcode.mowers.domain.vo

import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DirectionTest {

    @Test
    fun `turnLeft should return correct direction`() {
        assertEquals(Direction.WEST, Direction.NORTH.turnLeft())
        assertEquals(Direction.SOUTH, Direction.WEST.turnLeft())
        assertEquals(Direction.EAST, Direction.SOUTH.turnLeft())
        assertEquals(Direction.NORTH, Direction.EAST.turnLeft())
    }

    @Test
    fun `turnRight should return correct direction`() {
        assertEquals(Direction.EAST, Direction.NORTH.turnRight())
        assertEquals(Direction.SOUTH, Direction.EAST.turnRight())
        assertEquals(Direction.WEST, Direction.SOUTH.turnRight())
        assertEquals(Direction.NORTH, Direction.WEST.turnRight())
    }

    @Test
    fun `toString should return correct string representation`() {
        assertEquals("N", Direction.NORTH.toString())
        assertEquals("E", Direction.EAST.toString())
        assertEquals("S", Direction.SOUTH.toString())
        assertEquals("W", Direction.WEST.toString())
    }

    @Test
    fun `fromString should return correct direction`() {
        assertEquals(Direction.NORTH, Direction.fromString("N"))
        assertEquals(Direction.EAST, Direction.fromString("E"))
        assertEquals(Direction.SOUTH, Direction.fromString("S"))
        assertEquals(Direction.WEST, Direction.fromString("W"))
    }

    @Test
    fun `fromString should throw exception for invalid input`() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            Direction.fromString("INVALID")
        }
        assertEquals("Invalid direction: INVALID", exception.message)
    }
}