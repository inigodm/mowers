package com.seatcode.mowers.application

data class RobotCommand(
    val plateauSizeX: Int = 0,
    val plateauSizeY: Int = 0,
    val robots: List<RobotData> = emptyList())