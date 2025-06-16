package com.seatcode.mowers.infrastructure

import com.seatcode.mowers.application.CalculateFinalPositions
import com.seatcode.mowers.domain.robot.Position
import com.seatcode.mowers.infrastructure.parser.InputParser
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class MowersCLI(private val service: CalculateFinalPositions,
                private val inputParser: InputParser
) : CommandLineRunner {

    @Throws(Exception::class)
    override fun run(vararg args: String?) {
        try {
            val input = args.filterNotNull().joinToString(" ")
            val positions = runKata(input)
            drawResults(positions)
        } catch (e: Exception) {
            Consola.printLine("Error processing input: ${e.message}")
        }
    }

    fun runKata(input: String): List<Position> {
        return service.execute(inputParser.parse(input))
    }

    fun drawResults(positions: List<Position>) {
        positions.forEach { position ->
            Consola.printLine("${position.x.value} ${position.y.value} ${position.direction}")
        }
    }

    object Consola {
        fun printLine(mensaje: String) = println(mensaje)
    }
}