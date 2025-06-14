package com.seatcode.mowers

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MowersApplication

fun main(args: Array<String>) {
	runApplication<MowersApplication>(*args)
}
