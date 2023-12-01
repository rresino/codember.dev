package rresino.codember.task2023

import rresino.codember.util.ChallengeTask
import rresino.codember.util.FileUtils

object Challenge04 : ChallengeTask<String> {

    data class FileCheckSum(val fileContent: String, val checkSum: String) {

        private fun getOccurrences(letter: Char, line: String): Int = line.count { letter == it }

        fun calculateCheckSum(): String {
            return innerCalculateCheckSum(fileContent, arrayOf())
        }

        private fun innerCalculateCheckSum(contentToCheck: String, buffer: Array<Char>): String {
            if (contentToCheck.isEmpty()) {
                return buffer.joinToString(separator = "")
            }

            val head = contentToCheck[0]

            return if (getOccurrences(head, contentToCheck) == 1) {
                innerCalculateCheckSum(
                    contentToCheck.replace(head.toString(), ""), buffer + head)
            } else {
                innerCalculateCheckSum(
                    contentToCheck.replace(head.toString(), ""), buffer)
            }
        }

        fun validateCheckSum(): Boolean {
            return calculateCheckSum() == checkSum
        }
    }

    class FileValidator(private val maxValidations: Int) {

        fun validateFiles(files: List<String>): FileCheckSum {

            val checkSumFiles = files.map { parseLinesOfFiles(it) }

            return getLastValidCheckSum(
                checkSumFiles,
                lastValidFile = FileCheckSum("<none>", ""),
                count = 0)
        }

        private fun getLastValidCheckSum(
            filesToCheck: List<FileCheckSum>,
            lastValidFile: FileCheckSum,
            count: Int): FileCheckSum {

            if (filesToCheck.isEmpty() || count >= maxValidations) {
                return lastValidFile
            }

            val head = filesToCheck[0]
            val tail = filesToCheck.drop(1)

            return if (head.validateCheckSum()) {
                getLastValidCheckSum(tail, head, count + 1)
            } else {
                getLastValidCheckSum(tail, lastValidFile, count)
            }
        }

        companion object {
            fun parseLinesOfFiles(line: String): FileCheckSum {
                val chunks = line.trim().split("-", limit = 2)
                return FileCheckSum(chunks[0],chunks[1])
            }
        }

    }

    override fun run(filePath: String): String {
        val lines = FileUtils.readInput(filePath, cleanUp = true)
        return FileValidator(33).validateFiles(lines).checkSum
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println("Challenge 04:")
        val rs = Challenge04.runWithTime("files_quarantine.txt")
        println("Solution (${rs.first.inWholeMilliseconds}):")
        println(rs.second)
    }
}