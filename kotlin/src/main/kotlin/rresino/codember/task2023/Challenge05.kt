package rresino.codember.task2023

import rresino.codember.util.ChallengeTask
import rresino.codember.util.FileUtils

object Challenge05 : ChallengeTask<String> {

    data class User(val id: String?, val username: String?, val email: String?, val age: String?,
                    val location: String?) {

        private fun isLettersOrDigits(chars: String): Boolean {
            return chars.all { it.isLetterOrDigit() }
        }

        private fun isValidEmail(str: String?): Boolean =
            str?.let {
                it.isNotBlank() && it.matches(emailRegex.toRegex())
            } ?: false

        private fun isNumber(): Boolean = age?.let { str ->
            str.isNotBlank() && str.all { it.isDigit() }
        } ?: true

        fun isValidRequiredAlpha(str: String?): Boolean =
            str?.let { it.isNotBlank() && isLettersOrDigits(it) } ?: false

        fun validate(): Boolean =
                    isValidRequiredAlpha(id) &&
                    isValidRequiredAlpha(username) &&
                    isValidEmail(email) &&
                    isNumber()

        companion object {

            private val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"

            fun parseLineToUser(line: String): User {
                val chunks = line.split(",")

                return User(
                    id = chunks.getOrNull(0),
                    username = chunks.getOrNull(1),
                    email = chunks.getOrNull(2),
                    age = chunks.getOrNull(3),
                    location = chunks.getOrNull(4)
                )
            }
        }
    }

    override fun run(filePath: String): String {
        return FileUtils.readInput(filePath, cleanUp = true)
            .map { User.parseLineToUser(it) }
            .filter { !it.validate() }
            .map { it.username?.get(0)?:" " }
            .joinToString(separator = "")
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println("Challenge 05:")
        val rs = Challenge05.runWithTime("database_attacked.txt")
        println("Solution (${rs.first.inWholeMilliseconds}):")
        println(rs.second)
    }
}