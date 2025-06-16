package com.seatcode.mowers.domain.vo

@JvmInline
value class Height private constructor(val value: Int) {
    init {
        require(value > 0) { "Height must be greater than zero" }
    }

    companion object {
        fun of(value: Int): Height = Height(value)
    }

    override fun toString(): String = "$value"
}