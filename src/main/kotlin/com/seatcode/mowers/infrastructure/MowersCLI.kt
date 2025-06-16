package com.seatcode.mowers.infrastructure

import com.seatcode.mowers.application.CalculateFinalPositions
import com.seatcode.mowers.domain.Robot
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class MowersCLI(private val servicio: CalculateFinalPositions,
                private val inputParser: InputParser) : CommandLineRunner {

    @Throws(Exception::class)
    override fun run(vararg args: String?) {
        runKata(args.getOrNull(0) ?: "")
    }

    fun runKata(input: String): List<Robot.Position> {
        return servicio.execute(inputParser.parse(input))
    }
}