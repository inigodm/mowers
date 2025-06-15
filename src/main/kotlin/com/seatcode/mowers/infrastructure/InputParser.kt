package com.seatcode.mowers.infrastructure

import com.seatcode.mowers.application.RobotCommand

class InputParser {
    val regexPlateauSize = Regex("""(\d+)\s+(\d+)""") // Multispace?
    val regexRobotStatus = Regex("""(\d+)\s+(\d+)\s+([NSEW])""") // Multispace?
    val PLATEAU_SIZE_LINE_NUMBER = 0
    val ROBOT_INITIAL_STATE_LINE_NUMBER = 1

    fun parse(input: String): RobotCommand {

        val (plateauSizeX, plateauSizeY) = findPlateauSize(input)

        val (robotInitialX, robotInitialY, robotOrientation) = findRobotInitialState(input)

        return RobotCommand(
            plateauSizeX = plateauSizeX,
            plateauSizeY = plateauSizeY,
            robotX = robotInitialX,
            robotY = robotInitialY,
            robotOrientation = robotOrientation)
    }

    fun findPlateauSize(input: String): Pair<Int, Int> {
        val line = input.lines()[PLATEAU_SIZE_LINE_NUMBER].trim()
        return regexPlateauSize.matchEntire(line)
            ?.destructured
            ?.let { (x, y) -> Pair(x.toInt(), y.toInt()) }
            ?: throw InvalidInputError.becauseFirstLineShouldBeMapSize(line)
    }

    fun findRobotInitialState(input: String): Triple<Int, Int, String> {
        val line = input.lines()[ROBOT_INITIAL_STATE_LINE_NUMBER].trim()
        return regexRobotStatus.matchEntire(line)
            ?.destructured
            ?.let { (x, y, orientation) -> Triple(x.toInt(), y.toInt(), orientation) }
            ?: throw InvalidInputError.becauseSecondLineShouldBeRobot(line)
    }
}