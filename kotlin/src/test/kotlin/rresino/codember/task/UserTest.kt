package rresino.codember.task

import org.junit.jupiter.api.Test
import rresino.codember.task2023.Challenge05.User
import strikt.api.*
import strikt.assertions.*

class UserTest {

    @Test
    fun `parse a raw line with all the fields and get an user object`() {
        val rs = User.parseLineToUser("1a421fa,alex,alex9@gmail.com,18,Barcelona")

        expect {
            that(rs).isNotNull()
            that(rs.id).isEqualTo("1a421fa")
            that(rs.username).isEqualTo("alex")
            that(rs.email).isEqualTo("alex9@gmail.com")
            that(rs.age).isEqualTo("18")
            that(rs.location).isEqualTo("Barcelona")
        }
    }

    @Test
    fun `parse a raw line with blanks and get user object`() {
        val rs = User.parseLineToUser("494ee0,madeval,mdv@twitch.tv,,")

        expect {
            that(rs).isNotNull()
            that(rs.id).isEqualTo("494ee0")
            that(rs.username).isEqualTo("madeval")
            that(rs.email).isEqualTo("mdv@twitch.tv")
            that(rs.age).isEqualTo("")
            that(rs.location).isEqualTo("")
        }
    }

    @Test
    fun `parse a raw line with empty fields and get user object`() {
        val rs = User.parseLineToUser("494ee0,madeval,mdv@twitch.tv")

        expect {
            that(rs).isNotNull()
            that(rs.id).isEqualTo("494ee0")
            that(rs.username).isEqualTo("madeval")
            that(rs.email).isEqualTo("mdv@twitch.tv")
            that(rs.age).isNull()
            that(rs.location).isNull()
        }
    }

    @Test
    fun `validate ok for a valid user`() {
        val data = User("1a421fa","alex","alex9@gmail.com","18","Barcelona")
        val rs = data.validate()
        expectThat(rs).isTrue()
    }

    @Test
    fun `validate KO for a user with empty id`() {
        val data = User(null,"alex","alex9@gmail.com","18","Barcelona")
        val rs = data.validate()
        expectThat(rs).isFalse()
    }

    @Test
    fun `validate KO for a user with blank id`() {
        val data = User("","alex","alex9@gmail.com","18","Barcelona")
        val rs = data.validate()
        expectThat(rs).isFalse()
    }

    @Test
    fun `validate KO for a user with empty username`() {
        val data = User("1a421fa",null,"alex9@gmail.com","18","Barcelona")
        val rs = data.validate()
        expectThat(rs).isFalse()
    }

    @Test
    fun `validate KO for a user with blank username`() {
        val data = User("1a421fa","","alex9@gmail.com","18","Barcelona")
        val rs = data.validate()
        expectThat(rs).isFalse()
    }

    @Test
    fun `validate KO for a user with empty email`() {
        val data = User("1a421fa","alex",null,"18","Barcelona")
        val rs = data.validate()
        expectThat(rs).isFalse()
    }

    @Test
    fun `validate KO for a user with blank email`() {
        val data = User("1a421fa","alex","","18","Barcelona")
        val rs = data.validate()
        expectThat(rs).isFalse()
    }

    @Test
    fun `validate KO for a user with no valid email`() {
        val data = User("1a421fa","alex","aaaa.com","18","Barcelona")
        val rs = data.validate()
        expectThat(rs).isFalse()
    }

    @Test
    fun `validate KO for a user with no numeric age`() {
        val data = User("1a421fa","alex","alex9@gmail.com","1a8","Barcelona")
        val rs = data.validate()
        expectThat(rs).isFalse()
    }

    @Test
    fun `validate KO for a user with no alphanumeric id`() {
        val data = User("1a42_1fa","alex","alex9@gmail.com","18","Barcelona")
        val rs = data.validate()
        expectThat(rs).isFalse()
    }

    @Test
    fun `validate KO for a user with no alphanumeric username`() {
        val data = User("1a421fa","al*ex","alex9@gmail.com","18","Barcelona")
        val rs = data.validate()
        expectThat(rs).isFalse()
    }

}