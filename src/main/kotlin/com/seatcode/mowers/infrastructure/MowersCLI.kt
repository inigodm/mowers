package com.seatcode.mowers.infrastructure

import com.seatcode.mowers.application.CalculateFinalPositions
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class MowersCLI(private val servicio: CalculateFinalPositions) : CommandLineRunner {

    @Throws(Exception::class)
    override fun run(vararg args: String?) {
        runKata(args.getOrNull(0) ?: "")
    }

    fun runKata(input: String): String {
        return servicio.execute(input)
    }
}