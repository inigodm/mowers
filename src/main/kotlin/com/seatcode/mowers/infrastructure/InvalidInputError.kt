package com.seatcode.mowers.infrastructure


class InvalidInputError(val msg: String): RuntimeException(msg) {
    companion object {
        @JvmStatic
        fun becauseFirstLineShouldBeMapSize(input: String) =
            InvalidInputError("First line of input should be a map size. p.e.: '5 5', actual value: $input")

        @JvmStatic
        fun becauseSecondLineShouldBeRobot(input: String) =
            InvalidInputError("Second line of input should be a robot initial state. p.e.: '1 2 N', actual value: $input")
    }

}
