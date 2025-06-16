package com.seatcode.mowers.domain.vo

@JvmInline
value class Width private constructor(val value: Int) {
    init {
        require(value > 0) { "Width must be greater than zero" }
    }

    companion object {
        fun of(value: Int): Width = Width(value)
    }
}