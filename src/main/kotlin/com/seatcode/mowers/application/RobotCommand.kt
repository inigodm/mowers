package com.seatcode.mowers.application

import com.seatcode.mowers.domain.robot.Movement
import com.seatcode.mowers.domain.robot.Position
import com.seatcode.mowers.domain.vo.Direction
import com.seatcode.mowers.domain.robot.Robot
import com.seatcode.mowers.domain.robot.Robots
import com.seatcode.mowers.domain.vo.Height
import com.seatcode.mowers.domain.vo.Width
import com.seatcode.mowers.domain.vo.X
import com.seatcode.mowers.domain.vo.Y

data class RobotCommand(
    val plateauSizeX: Int,
    val plateauSizeY: Int,
    val robots: List<RobotData>) {

    fun plateauHeight(): Height {
        return Height.of(plateauSizeY)
    }

    fun plateauWidth(): Width {
        return Width.of(plateauSizeX)
    }

    fun robotList(): Robots {
        return Robots(robots.map { robotData ->
                Robot(
                    position = Position(
                        x = X.of(robotData.x),
                        y = Y.of(robotData.y),
                        direction = Direction.fromString(robotData.orientation)
                    ),
                    movements = robotData.statusModifiers.map { Movement.fromString(it) }
                )
            }
        )
    }
}