package com.seatcode.mowers.application

import com.seatcode.mowers.domain.PlateauMap
import com.seatcode.mowers.domain.robot.Position
import org.springframework.stereotype.Service

@Service
class CalculateFinalPositions {
    fun execute(command: RobotCommand) : List<Position> {
        val plateauMap = PlateauMap(command.plateauWidth(), command.plateauHeight(), command.robotList())
        return plateauMap.calculateNextRobotPositions()
    }
}