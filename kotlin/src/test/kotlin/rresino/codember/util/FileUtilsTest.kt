package rresino.codember.util

import org.junit.jupiter.api.Test

import strikt.api.*
import strikt.assertions.*

class FileUtilsTest {

    @Test
    fun `readInput a file`() {
        val filePath = "message_01_example0.txt"

        val result = FileUtils.readInput(filePath, cleanUp = true)

        expectThat(result)
            .isA<List<String>>()
            .hasSize(1)

    }

}