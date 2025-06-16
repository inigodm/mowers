package com.seatcode.mowers.application

data class RobotCommand(
    val plateauSizeX: Int,
    val plateauSizeY: Int,
    val robots: List<RobotData>)