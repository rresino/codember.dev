package rresino.codember.task

import org.junit.jupiter.api.Test
import rresino.codember.task2023.Challenge03.Password
import rresino.codember.task2023.Challenge03.PasswordPolicy
import strikt.api.*
import strikt.assertions.*

class PasswordTest {

    @Test
    fun `parse a file row and get the password and policy`() {
        val rs = Password.parseLine("2-4 f: fgff")

        expect {
            that(rs.policy.minimumOccurrences).isEqualTo(2)
            that(rs.policy.maxOccurrences).isEqualTo(4)
            that(rs.policy.letter).isEqualTo('f')
            that(rs.value).isEqualTo("fgff")
        }
    }

    @Test
    fun `get ocurrences of a letter`() {
        val rs = Password.getOccurrences('z', "zzzsg")

        expectThat(rs).isEqualTo(3)
    }

    @Test
    fun `check the policy for a simple right password`() {
        val pwd = Password("fgff", PasswordPolicy(2, 4, 'f'))
        val rs = pwd.checkPolicy()

        expectThat(rs).isEqualTo(true)
    }

    @Test
    fun `check the policy for a invalid password with less occurrences`() {
        val pwd = Password("zzzsg", PasswordPolicy(4, 6, 'z'))
        val rs = pwd.checkPolicy()

        expectThat(rs).isEqualTo(false)
    }

    @Test
    fun `check the policy for a invalid password with too much occurrences`() {
        val pwd = Password("hhhhhh", PasswordPolicy(1, 5, 'h'))
        val rs = pwd.checkPolicy()

        expectThat(rs).isEqualTo(false)
    }
}