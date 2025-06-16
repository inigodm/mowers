package com.seatcode.mowers.domain

import com.seatcode.mowers.domain.vo.X
import com.seatcode.mowers.domain.vo.Y

data class Robot(val position: Position, val movements: List<Movement>) {

    data class Position(
        val x: X,
        val y: Y,
        var direction: Direction
    )

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
}