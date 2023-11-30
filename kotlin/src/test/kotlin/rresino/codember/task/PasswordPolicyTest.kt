package rresino.codember.task

import org.junit.jupiter.api.Test
import rresino.codember.task2023.Challenge03
import strikt.api.*
import strikt.assertions.*

class PasswordPolicyTest {

    @Test
    fun `parse a simple line with 2 and 4 values and f`() {
        val rs = Challenge03.PasswordPolicy.parsePolicy("2-4 f")

        expect {
            that(rs.letter).isEqualTo('f')
            that(rs.minimumOccurrences).isEqualTo(2)
            that(rs.maxOccurrences).isEqualTo(4)
        }
    }


}