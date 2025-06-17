package com.seatcode.mowers.infrastructure

import com.seatcode.mowers.domain.MowerAdapter
import com.seatcode.mowers.domain.robot.Movement
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class MowerManager : MowerAdapter {

    override fun moveRobot(id: UUID, movement: Movement) {
        ConsoleMowler.printLine("Moving robot with id: $id, doing $movement")
    }

    object ConsoleMowler {
        fun printLine(mensaje: String) = println(mensaje)
    }
}