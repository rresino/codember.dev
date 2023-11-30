package rresino.codember.util

import kotlin.time.Duration
import kotlin.time.measureTime

interface ChallengeTask<T> {

    fun runWithTime(filePath: String): Pair<Duration, T> = trackTime(filePath){ s -> run(s) }

    fun run(filePath: String): T

    fun trackTime(param: String, task: (String) -> T): Pair<Duration, T> {

        var result: T

        val time = measureTime{ result = task(param) }

        return Pair(time, result)
    }

}