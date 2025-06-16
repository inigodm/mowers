package com.seatcode.mowers.domain.robot

enum class Movement {
    MOVE,
    LEFT,
    RIGHT;

    override fun toString(): String {
        return when (this) {
            MOVE -> "M"
            LEFT -> "L"
            RIGHT -> "R"
        }
    }

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