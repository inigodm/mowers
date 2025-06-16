package com.seatcode.mowers.domain.robot

import com.seatcode.mowers.domain.vo.Direction
import com.seatcode.mowers.domain.vo.X
import com.seatcode.mowers.domain.vo.Y

data class Position(
    val x: X,
    val y: Y,
    var direction: Direction
) {

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
