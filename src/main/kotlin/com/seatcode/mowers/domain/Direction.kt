package com.seatcode.mowers.domain

import com.seatcode.mowers.domain.vo.X
import com.seatcode.mowers.domain.vo.Y

enum class Direction(val dx: X, val dy: Y) {
    NORTH(X.of(0), Y.of(1)),
    EAST(X.of(1),Y.of(0)),
    SOUTH(X.of(0),Y.of(-1)),
    WEST(X.of(-1),Y.of(0));

    fun turnLeft(): Direction {
        return when (this) {
            NORTH -> WEST
            WEST -> SOUTH
            SOUTH -> EAST
            EAST -> NORTH
        }
    }

    fun turnRight(): Direction {
        return when (this) {
            NORTH -> EAST
            EAST -> SOUTH
            SOUTH -> WEST
            WEST -> NORTH
        }
    }

    override fun toString(): String {
        return when (this) {
            NORTH -> "N"
            EAST -> "E"
            SOUTH -> "S"
            WEST -> "W"
        }
    }

    companion object {
        fun fromString(value: String): Direction {
            return when (value.uppercase()) {
                "N" -> NORTH
                "E" -> EAST
                "S" -> SOUTH
                "W" -> WEST
                else -> throw IllegalArgumentException("Invalid direction: $value")
            }
        }
    }
}