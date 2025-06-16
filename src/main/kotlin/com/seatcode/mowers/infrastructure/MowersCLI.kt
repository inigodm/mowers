package com.seatcode.mowers.infrastructure

import com.seatcode.mowers.application.CalculateFinalPositions
import com.seatcode.mowers.domain.robot.Position
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class MowersCLI(private val service: CalculateFinalPositions,
                private val inputParser: InputParser) : CommandLineRunner {

    @Throws(Exception::class)
    override fun run(vararg args: String?) {
        val positions = runKata(args.getOrNull(0) ?: "")
        drawResults(positions)
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