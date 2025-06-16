package com.seatcode.mowers.infrastructure

import com.seatcode.mowers.application.RobotCommand
import com.seatcode.mowers.application.RobotData
import org.springframework.stereotype.Component

@Component
class InputParser {
    val regexPlateauSize = Regex("""(\d+)\s+(\d+)""") // Multispace?
    val regexRobotStatus = Regex("""(\d+)\s+(\d+)\s+([NSEW])""") // Multispace?
    val regexRobotStatusModifiers = Regex("""([LRM]+)""")
    val PLATEAU_SIZE_LINE_NUMBER = 0
    val ROBOT_INITIAL_STATE_LINE_NUMBER = 0
    val ROBOT_STATUS_MODIFIERS_LINE_NUMBER = 1

    fun parse(input: String): RobotCommand {
        val lines = input.lines().map { it.trim().uppercase() }
        val (plateauSizeX, plateauSizeY) = findPlateauSize(input)
        val robots = findRobots(lines.drop(1))
        return RobotCommand(
            plateauSizeX = plateauSizeX,
            plateauSizeY = plateauSizeY,
            robots = robots)
    }

    fun findRobots(lines: List<String>): List<RobotData> {
        if (areNotTwoLinesForEachRobot(lines)) {
            throw InvalidInputError.becauseInvalidNumberOfLines(lines.size)
        }
        return lines
            .windowed(2, 2, partialWindows = false)
            .map { (initialState, modifiers) ->
                val (x, y, orientation) = findRobotInitialState("$initialState\n$modifiers")
                val mods = findRobotModifiers("$initialState\n$modifiers")
                RobotData(x, y, orientation, mods)
            }
    }

    private fun areNotTwoLinesForEachRobot(lines: List<String>): Boolean = lines.size % 2 != 0 || lines.size < 2

    fun findRobotModifiers(input: String): List<String> {
        val line = input.lines()[ROBOT_STATUS_MODIFIERS_LINE_NUMBER].trim()
        return regexRobotStatusModifiers.matchEntire(line)
            ?.destructured
            ?.let { (word) ->word.map { it.toString()} }
            ?: throw InvalidInputError.becauseThirdLineShouldContainModifiers(line)
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