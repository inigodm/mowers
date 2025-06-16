package com.seatcode.mowers.domain.robot

enum class Movement {
    MOVE,
    LEFT,
    RIGHT;

    companion object {
        fun fromString(value: String): Movement {
            return when (value.uppercase()) {
                "M" -> MOVE
                "L" -> LEFT
                "R" -> RIGHT
                else -> throw IllegalArgumentException("Unknown movement: $value")
            }
        }
    }
}