package com.seatcode.mowers.infrastructure


class InvalidInputError(val msg: String): RuntimeException(msg) {
    companion object {
        @JvmStatic
        fun becauseFirstLineShouldBeMapSize(input: String) =
            InvalidInputError("First line of input should be a map size. p.e.: '5 5', actual value: $input")

        @JvmStatic
        fun becauseSecondLineShouldBeRobot(input: String) =
            InvalidInputError("Second line of input should be a robot initial state. p.e.: '1 2 N', actual value: $input")

        @JvmStatic
        fun becauseThirdLineShouldContainModifiers(line: String) =
            InvalidInputError("Third line of input should be a robot state mutation list: A word with valid letters: L, R, M, as p.e.: 'LRMRLRRMMM', actual value: $line")

        @JvmStatic
        fun becauseInvalidNumberOfLines(lines: Int) =
            InvalidInputError("Invalid number of lines in input: $lines. It should be an even number greater than 2, as each robot has two lines of input.")
    }

}
