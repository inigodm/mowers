package com.seatcode.mowers.domain.robot

class Robots(val robots: List<Robot> = mutableListOf()) {

    fun <R> map(transform: (Robot) -> R): List<R> {
        return robots.map(transform)
    }
}