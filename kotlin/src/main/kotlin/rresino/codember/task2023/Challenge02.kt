package rresino.codember.task2023

import rresino.codember.util.FileUtils
import kotlin.time.Duration
import kotlin.time.measureTime

object Challenge02 {

    data class InterpreterStatus(val value: Int = 0, val outputLog: String = "") {

        fun increase() = copy(value = value + 1)

        fun decrease() = copy(value = value - 1)

        fun multiply() = copy(value = value * value)

        fun logCurrentValue() = copy(outputLog = "$outputLog$value")

    }

    fun run(filePath: String): Pair<Duration, String> {

        var rs = ""
        val time = measureTime {
            FileUtils.readInput(filePath, cleanUp = true)
                .joinToString { it }
                .let { rs = execute(it) }
        }
        return Pair(time, rs)
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
        val rs = run("message_02.txt")
        println("Solution (${rs.first.inWholeMilliseconds}):")
        println(rs.second)
    }

}