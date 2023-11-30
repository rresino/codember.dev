package rresino.codember.task

import org.junit.jupiter.api.Test
import rresino.codember.task2023.Challenge01

import strikt.api.*
import strikt.assertions.*

class Challenge01Test {

    @Test
    fun `execute run using example 0`() {

        val filePath = "message_01_example0.txt"

        val result = Challenge01.run(filePath)

        expectThat(result)
            .isA<String>()
            .isEqualTo("gato2perro3coche1sol1")
    }

    @Test
    fun `execute run using example 1`() {

        val filePath = "message_01_example1.txt"

        val result = Challenge01.run(filePath)

        expectThat(result)
            .isA<String>()
            .isEqualTo("llaves2casa3")
    }

    @Test
    fun `execute run using example 2`() {

        val filePath = "message_01_example2.txt"

        val result = Challenge01.run(filePath)

        expectThat(result)
            .isA<String>()
            .isEqualTo("taza2ta1za1")
    }

    @Test
    fun `execute run using example 3`() {

        val filePath = "message_01_example3.txt"

        val result = Challenge01.run(filePath)

        expectThat(result)
            .isA<String>()
            .isEqualTo("casas1casa1casasas1")
    }
}