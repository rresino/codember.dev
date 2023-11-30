package rresino.codember.task2023

import rresino.codember.util.ChallengeTask
import rresino.codember.util.FileUtils

object Challenge01 : ChallengeTask<String> {

    override fun run(filePath: String): String {

        return FileUtils
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

    @JvmStatic
    fun main(args: Array<String>) {
        println("Challenge 01:")
        val rs = Challenge01.runWithTime("message_01.txt")
        println("Solution (${rs.first.inWholeMilliseconds}):")
        println(rs.second)
    }

}