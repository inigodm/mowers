package com.seatcode.mowers.domain.vo

@JvmInline
value class Y(val value: Int) {

    companion object {
        fun of(value: Int): Y = Y(value)
    }

    operator fun plus(other: Y): Y {
        return Y(this.value + other.value)
    }

    override fun toString(): String = "$value"
}