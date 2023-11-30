package rresino.codember.task

import org.junit.jupiter.api.Test
import rresino.codember.task2023.Challenge03.Password
import rresino.codember.task2023.Challenge03.PasswordPolicyChecker
import strikt.api.expect
import strikt.assertions.isA
import strikt.assertions.isEqualTo
import strikt.assertions.isNotNull
import strikt.assertions.isNull

class Challenge03Test {

    @Test
    fun `check all pwds because max invalid pwd not reach and return last invalid pwd`() {

        val data = listOf(
            "2-4 f: fgff",
            "4-6 z: zzzsg",
            "1-6 h: hhhhhh",
        )

        val rs = PasswordPolicyChecker(999, data).run()

        expect {
            that(rs)
                .isNotNull()
                .isA<Password>()
            that(rs?.value).isEqualTo("zzzsg")
        }
    }

    @Test
    fun `check pwd until max invalid pwd reach`() {
        val data = listOf(
            "2-4 f: fgff",
            "4-6 z: 1zzzsg",
            "4-6 z: z2zzsg",
            "4-6 z: zz3zsg",
            "1-6 h: hhhhhh",
            "4-6 z: z4zzsg",
            "1-6 h: hhhhhh",
            "4-6 z: zq5zzsg",
            "4-6 z: zz6zsg",
            "4-6 z: zzezs7g",
            "4-6 z: 8zzzsg",
        )

        val rs = PasswordPolicyChecker(5, data).run()

        expect {
            that(rs)
                .isNotNull()
                .isA<Password>()
            that(rs?.value).isEqualTo("zq5zzsg")
        }
    }

    @Test
    fun `check all pwds not invalid pwd found`() {
        val data = listOf(
            "2-4 f: fgff",
            "1-6 h: hhhhhh",
            "2-4 f: fgff",
            "1-6 h: hhhhhh",
            "1-16 z: zq5zzsg",
            "1-26 z: zz6zsg",
            "1-36 z: zzezs7g",
            "1-56 z: 8zzzsg",
        )

        val rs = PasswordPolicyChecker(5, data).run()

        expect {
            that(rs).isNull()
        }
    }

}