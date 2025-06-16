package com.seatcode.mowers.domain.vo

@JvmInline
value class X (val value: Int) {

    companion object {
        fun of(value: Int): X = X(value)
    }

    operator fun plus(other: X): X {
        return X(this.value + other.value)
    }

    override fun toString(): String = "$value"
}