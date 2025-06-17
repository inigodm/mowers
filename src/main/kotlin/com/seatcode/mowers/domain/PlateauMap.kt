package com.seatcode.mowers.domain

import com.seatcode.mowers.domain.robot.Position
import com.seatcode.mowers.domain.robot.Robot
import com.seatcode.mowers.domain.robot.Robots
import com.seatcode.mowers.domain.vo.Height
import com.seatcode.mowers.domain.vo.Width
import com.seatcode.mowers.domain.vo.X
import com.seatcode.mowers.domain.vo.Y

class PlateauMap {
    private val width: Width
    private val height: Height
    private val robots: Robots

    constructor(width: Width, height: Height, robots: Robots) {
        this.width = width
        this.height = height
        this.robots = robots
    }

    fun isValidPosition(x: X, y: Y): Boolean {
        return x.value in 0 until width.value  && y.value in 0 until height.value
    }

    fun moveRobotsToNextPositions(mowerAdapter: MowerAdapter): List<Position> = robots.map { robot -> calculateNextRobotPosition(robot, mowerAdapter) }

    private fun calculateNextRobotPosition(robot: Robot, mowerAdapter: MowerAdapter) : Position {
        var currentPosition = robot.position
        for (movement in robot.movements) {
            val newPosition = currentPosition.movementTo(movement)
            if (!isValidPosition(newPosition.x, newPosition.y)) {
                println("Moving robot from ${robot.position} to $newPosition will drop it outside the plateau, stopping it")
                break
            }
            mowerAdapter.moveRobot(robot.id, movement)
            currentPosition = newPosition
        }
        return currentPosition
    }
}