package rresino.codember.task2023

import rresino.codember.util.ChallengeTask
import rresino.codember.util.FileUtils

object Challenge02 : ChallengeTask<String> {

    data class InterpreterStatus(val value: Int = 0, val outputLog: String = "") {

        fun increase() = copy(value = value + 1)

        fun decrease() = copy(value = value - 1)

        fun multiply() = copy(value = value * value)

        fun logCurrentValue() = copy(outputLog = "$outputLog$value")

    }

    override fun run(filePath: String): String {

        return FileUtils.readInput(filePath, cleanUp = true)
                .joinToString { it }
                .let { execute(it) }
    }

    fun execute(inputStr: String): String {
        val status = inputStr.fold(InterpreterStatus()){ acc, c ->
            when(c) {
                '#' -> acc.increase()
                '@' -> acc.decrease()
                '*' -> acc.multiply()
                '&' -> acc.logCurrentValue()
                else -> acc
            }
        }
        return status.outputLog
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println("Challenge 02:")
        val rs = runWithTime("message_02.txt")
        println("Solution (${rs.first.inWholeMilliseconds}):")
        println(rs.second)
    }

}