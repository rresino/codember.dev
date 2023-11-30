package rresino.codember.task

import org.junit.jupiter.api.Test
import rresino.codember.task2023.Challenge02
import strikt.api.*
import strikt.assertions.*

class Challenge02Test {

    @Test
    fun `solve example 1, simple string with only 1 character as result`() {
        val rs = Challenge02.execute("##*&")

        expectThat(rs)
            .isA<String>()
            .isEqualTo("4")
    }

    @Test
    fun `solve example 2, complex string with multiple characters as result`() {
        val rs = Challenge02.execute("&##&*&@&")

        expectThat(rs)
            .isA<String>()
            .isEqualTo("0243")
    }
}