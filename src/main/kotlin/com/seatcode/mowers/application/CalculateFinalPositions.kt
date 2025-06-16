package com.seatcode.mowers.application

import com.seatcode.mowers.domain.PlateauMap
import com.seatcode.mowers.domain.Robot
import org.springframework.stereotype.Service

@Service
class CalculateFinalPositions {
    fun execute(command: RobotCommand) : List<Robot.Position> {
        val plateauMap = PlateauMap(command.plateauWidth(), command.plateauHeight(), command.robotList())
        return plateauMap.moveRobots()
    }
}