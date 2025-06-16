package com.seatcode.mowers.infrastructure


class InvalidInputError(msg: String): RuntimeException(msg) {
    companion object {
        @JvmStatic
        fun becausePlateauSizeLineIsInIncorrectFormat(input: String) =
            InvalidInputError("Plateau size input should respect format:" +
                    "'<digits><spaces><digits>'. p.e.: '5 5', actual value: $input")

        @JvmStatic
        fun becauseRobotInitialStateLineIsInIncorrectFormat(input: String) =
            InvalidInputError("Robot initial state input should respect format:" +
                    "'<digits><spaces><digits><one of NWSE>'. p.e.: '1 2 N', actual value: $input")

        @JvmStatic
        fun becauseRobotMovementsLineIsInIncorrectFormat(line: String) =
            InvalidInputError("Robot movements input should respect format:'<any of LRM one or more times>'." +
                    " p.e.: 'LRMRLRRMMM', actual value: $line")

        @JvmStatic
        fun becauseInvalidNumberOfLines(lines: Int) =
            InvalidInputError("Invalid number of lines in input: $lines. " +
                    "It should be an even number greater than 2, as each robot has two lines of input.")
    }

}
