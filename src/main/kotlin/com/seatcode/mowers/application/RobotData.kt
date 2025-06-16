package com.seatcode.mowers.application

data class RobotData (
    val x: Int,
    val y: Int,
    val orientation: String,
    val statusModifiers: List<String>)