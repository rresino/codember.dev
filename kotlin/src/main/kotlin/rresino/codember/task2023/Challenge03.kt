package rresino.codember.task2023

import rresino.codember.util.ChallengeTask
import rresino.codember.util.FileUtils

object Challenge03 : ChallengeTask<String> {

    data class PasswordPolicy(val minimumOccurrences: Int, val maxOccurrences: Int, val letter: Char) {

        companion object {
            fun parsePolicy(rawStr: String): PasswordPolicy {
                val chunks = rawStr.split("-", " ")

                return PasswordPolicy(chunks[0].toInt(), chunks[1].toInt(), chunks[2][0])
            }
        }
    }

    data class Password(val value: String, val policy: PasswordPolicy) {

        fun checkPolicy(): Boolean {
            val occurrences = getOccurrences(policy.letter, value)
            return occurrences >= policy.minimumOccurrences && occurrences <= policy.maxOccurrences
        }

        companion object {

            fun getOccurrences(letter: Char, line: String): Int = line.count { letter == it }

            fun parseLine(line: String): Password {
                val chunks = line.split(":", limit = 2)

                return Password(chunks[1].trim(), PasswordPolicy.parsePolicy(chunks[0].trim()))
            }
        }
    }

    class PasswordPolicyChecker(val maxInvalidChecks:Int, val lines: List<String>) {

        fun run(): Password? = checkPassword(invalidPwdFound = 0, lastInvalidPwd = null, linesToCheck = lines)

        private fun checkPassword(
            invalidPwdFound: Int,
            lastInvalidPwd: Password?,
            linesToCheck: List<String>,): Password? {

            if (invalidPwdFound >= maxInvalidChecks || linesToCheck.isEmpty()) {
                return lastInvalidPwd
            }

            val pwd = Password.parseLine(linesToCheck.first())

            return if (pwd.checkPolicy()) {
                checkPassword(invalidPwdFound, lastInvalidPwd, linesToCheck.drop(1))
            } else {
                checkPassword(invalidPwdFound + 1, pwd, linesToCheck.drop(1))
            }
        }
    }

    override fun run(filePath: String): String {
        return let {
            val lines = FileUtils.readInput(filePath, cleanUp = true)
            PasswordPolicyChecker(42, lines).run()?.value?:""
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println("Challenge 03:")
        val rs = runWithTime("message_03.txt")
        println("Solution (${rs.first.inWholeMilliseconds}):")
        println(rs.second)
    }

}