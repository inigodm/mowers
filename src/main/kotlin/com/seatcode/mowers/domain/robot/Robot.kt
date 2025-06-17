package com.seatcode.mowers.domain.robot

import java.util.UUID


data class Robot(val id : UUID = UUID.randomUUID(), val position: Position, val movements: List<Movement>)