package rresino.codember.task

import org.junit.jupiter.api.Test
import rresino.codember.task2023.Challenge04
import rresino.codember.task2023.Challenge04.FileValidator
import strikt.api.*
import strikt.assertions.*

class FileValidatorTest {

    @Test
    fun `parse line of file + checksum`() {
        val data = "xyzz33-xy"
        val rs = FileValidator.parseLinesOfFiles(data)

        expectThat(rs)
            .isEqualTo(Challenge04.FileCheckSum("xyzz33","xy"))
    }

    @Test
    fun `validate file return last valid file content`() {
        val data = listOf(
            "xyzz33-xy",
            "abcca1-ab1",
            "abbc11-ca",
            )

        val rs = FileValidator(99).validateFiles(data)

        expectThat(rs)
            .isEqualTo(Challenge04.FileCheckSum("xyzz33", "xy"))
    }

    @Test
    fun `validate file return first invalid file content because limit is reach`() {
        val data = listOf(
            "abcca1-ab1",
            "xyzz33-xy",
            "abbc11-ca",
            "abbc11-00",
            "uWWKcD3b-uKcD3b",
        )

        val rs = FileValidator(1).validateFiles(data)

        expectThat(rs)
            .isEqualTo(Challenge04.FileCheckSum("xyzz33", "xy"))
    }
}