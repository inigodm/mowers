package com.seatcode.mowers.domain

import com.seatcode.mowers.domain.robot.Movement
import java.util.UUID

interface MowerAdapter {
    fun moveRobot(id: UUID, movement: Movement )
}