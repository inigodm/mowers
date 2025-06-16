package com.seatcode.mowers.domain

import com.seatcode.mowers.domain.vo.Height
import com.seatcode.mowers.domain.vo.Width
import com.seatcode.mowers.domain.vo.X
import com.seatcode.mowers.domain.vo.Y

class PlateauMap {
    private val width: Width
    private val height: Height
    private val robots: List<Robot>

    constructor(width: Width, height: Height, robots: List<Robot>) {
        this.width = width
        this.height = height
        this.robots = robots
    }

    fun isValidPosition(x: X, y: Y): Boolean {
        return x.value in 0 until width.value + 1 && y.value in 0 until height.value + 1
    }

    fun moveRobots(): List<Robot.Position> = robots.map { robot -> moveRobot(robot) }

    private fun moveRobot(robot: Robot) : Robot.Position {
        var currentPosition = robot.position
        for (movement in robot.movements) {
            val newPosition = currentPosition.movementTo(movement)
            if (!isValidPosition(newPosition.x, newPosition.y)) {
                println("Moving robot from ${robot.position} to $newPosition will drop it outside the plateau, stopping it")
                break
            }
            currentPosition = newPosition
        }
        return currentPosition
    }
}