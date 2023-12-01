package rresino.codember.task

import org.junit.jupiter.api.Test
import rresino.codember.task2023.Challenge04.FileCheckSum
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class FileCheckSumTest {

    @Test
    fun `calculate checksum for a simple file`() {
        val data = FileCheckSum(fileContent = "xyzz33", checkSum = "xy")
        val rs = data.calculateCheckSum()
        expectThat(rs).isEqualTo("xy")
    }

    @Test
    fun `simple file has a valid checksum`() {
        val data = FileCheckSum(fileContent = "xyzz33", checkSum = "xy")
        val rs = data.validateCheckSum()
        expectThat(rs).isEqualTo(true)
    }

    @Test
    fun `calculate checksum for a content file with duplicate`() {
        val data = FileCheckSum(fileContent = "abcca1", checkSum = "ab1")
        val rs = data.calculateCheckSum()
        expectThat(rs).isEqualTo("b1")
    }

    @Test
    fun `content file has a invalid checksum bacause has characters with duplicates`() {
        val data = FileCheckSum(fileContent = "abcca1", checkSum = "ab1")
        val rs = data.validateCheckSum()
        expectThat(rs).isEqualTo(false)
    }

    @Test
    fun `calculate checksum for a content file with invalid order`() {
        val data = FileCheckSum(fileContent = "abbc11", checkSum = "ab1")
        val rs = data.calculateCheckSum()
        expectThat(rs).isEqualTo("ac")
    }

    @Test
    fun `content file with duplicate and wrong order in checksum`() {
        val data = FileCheckSum(fileContent = "abbc11", checkSum = "ab1")
        val rs = data.validateCheckSum()
        expectThat(rs).isEqualTo(false)
    }

}