package com.seatcode.mowers.application

import com.seatcode.mowers.domain.Direction
import com.seatcode.mowers.domain.Robot
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

    fun robotList(): List<Robot> {
        return robots.map { robotData ->
            Robot(
                position = Robot.Position(
                    x = X.of(robotData.x),
                    y = Y.of(robotData.y),
                    direction = Direction.fromString(robotData.orientation)),
                movements = robotData.statusModifiers.map { Robot.Movement.fromString(it) }
            )
        }
    }
}