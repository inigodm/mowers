package com.seatcode.mowers.infrastructure

import com.seatcode.mowers.application.RobotCommand

class InputParser {
    fun parse(input: String): RobotCommand {
        val linea = input.lines().get(0)
        val regexPlateauSize = Regex("""(\d+)\s+(\d+)""") // Multispace?

        return regexPlateauSize.matchEntire(linea)
            ?.destructured
            ?.let { (x, y) -> RobotCommand(x.toInt(), y.toInt()) }
            ?: throw InvalidInputError.becauseFirstLineShouldBeMapSize(input)
    }
}