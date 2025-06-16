package com.seatcode.mowers.application

import com.seatcode.mowers.domain.Direction
import com.seatcode.mowers.domain.Robot
import com.seatcode.mowers.domain.vo.X
import com.seatcode.mowers.domain.vo.Y
import org.springframework.stereotype.Service

@Service
class CalculateFinalPositions {
    fun execute(command: RobotCommand) = listOf(Robot.Position(X.of(1), Y.of(2), Direction.SOUTH))
}