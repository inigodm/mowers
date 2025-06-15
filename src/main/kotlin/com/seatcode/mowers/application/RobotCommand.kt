package com.seatcode.mowers.application

data class RobotCommand(
    val plateauSizeX: Int = 0,
    val plateauSizeY: Int = 0,
    val robotX: Int = 0,
    val robotY: Int = 0,
    val robotOrientation: String = "",
    val robotStatusModifiers: List<String> = emptyList())