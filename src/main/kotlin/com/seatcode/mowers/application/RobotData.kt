package com.seatcode.mowers.application

data class RobotData (
    val x: Int = 0,
    val y: Int = 0,
    val orientation: String = "",
    val statusModifiers: List<String> = emptyList())