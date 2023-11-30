package rresino.codember.task2023

import rresino.codember.util.FileUtils
import kotlin.time.Duration
import kotlin.time.measureTime

object Challenge01 {

    fun run(filePath: String): Pair<Duration, String> {

        var rs = ""
        val time = measureTime {
            rs = FileUtils
                .readInput(filePath, cleanUp = true)
                .joinToString { it }
                .lowercase()
                .split(" ")
                .groupBy { it }
                .mapValues { it.value.size }
                .toList()
                .map { "${it.first}${it.second}" }
                .joinToString(separator = "") { it }
        }
        return Pair(time, rs)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println("Challenge 01:")
        val rs = run("message_01.txt")
        println("Solution (${rs.first.inWholeMilliseconds}):")
        println(rs.second)
    }

}