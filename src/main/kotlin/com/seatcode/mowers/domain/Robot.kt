package com.seatcode.mowers.domain

import com.seatcode.mowers.domain.vo.X
import com.seatcode.mowers.domain.vo.Y

data class Robot(val position: Position, val movements: List<Movement>) {

    data class Position(
        val x: X,
        val y: Y,
        var direction: Direction) {

        fun movementTo(movement: Movement): Position {
            val newPosition = when (movement) {
                Movement.MOVE -> {
                    val (x1, y1) = moveForward()
                    Position(x1, y1, direction
                    )
                }
                Movement.LEFT -> Position(x, y, turnLeft()
                )
                Movement.RIGHT -> Position(x, y, turnRight()
                )
            }
            return newPosition
        }
        fun turnLeft() = direction.turnLeft()
        fun turnRight() = direction.turnRight()
        fun moveForward() = Pair(x + direction.dx, y + direction.dy)
    }

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