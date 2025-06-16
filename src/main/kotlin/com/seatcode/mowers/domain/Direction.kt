package com.seatcode.mowers.domain

enum class Direction(val dx: Int, val dy: Int) {
    NORTH(1,0),
    EAST(0,1),
    SOUTH(-1,0),
    WEST(-1,0);

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