package com.abloom.domain.qna.model

import com.abloom.domain.question.model.Category
import com.abloom.domain.question.model.Question
import com.abloom.domain.user.model.Sex
import com.abloom.domain.user.model.User
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.assertj.core.api.Assertions.assertThatIllegalStateException
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import java.time.LocalDate
import java.time.LocalDateTime

internal class QnaTest {

    @Test
    fun `로그인 유저가 피앙세와 연결되어 있다면 Qna를 생성할 때 fianceUser가 null이면 에러가 발생한다`() {
        val loginUser = User(fianceId = "asdf")

        assertThatIllegalArgumentException().isThrownBy {
            Qna(loginUser = loginUser)
        }.withMessage("로그인 유저는 연결되어 있기 때문에 fianceUser가 null일리 없습니다.")
    }

    @Test
    fun `로그인 유저가 답변하지 않았을 때 로그인 유저의 답변을 조회하면 에러가 발생한다`() {
        val qna = Qna(loginUser = User(), loginUserAnswer = null)

        assertThatIllegalStateException().isThrownBy {
            qna.loginUserAnswer
        }.withMessage("로그인 유저가 답변했을 때만 로그인 유저의 답변을 조회할 수 있습니다.")
    }

    @Test
    fun `피앙세와 연결되어 있지 않을 때 피앙세의 답변이 잠겨있는지 조회하면 에러가 발생한다`() {
        val qna = Qna(loginUser = User())

        assertThatIllegalStateException().isThrownBy {
            qna.isFianceAnswerLocked
        }.withMessage("피앙세와 연결되어 있을 때만 피앙세 답변이 잠겨있는지 조회할 수 있습니다.")
    }

    @Test
    fun `피앙세 답변이 잠겨있을 때 피앙세가 답변했는지 조회하면 에러가 발생한다`() {
        val fiance = User()
        val loginUser = User(fianceId = fiance.id)
        val qna = Qna(loginUser = loginUser, fiance = fiance)

        assertThatIllegalStateException().isThrownBy {
            qna.isFianceAnswered
        }.withMessage("피앙세 답변이 잠겨있지 않을 때만 피앙세가 답변했는지 조회할 수 있습니다.")
    }

    @Test
    fun `피앙세가 답변하지 않았을 때 피앙세의 답변을 조회하면 에러가 발생한다`() {
        val fiance = User()
        val loginUser = User(fianceId = fiance.id)
        val qna = Qna(loginUser = loginUser, fiance = fiance, loginUserAnswer = Answer("asf"))

        assertThatIllegalStateException().isThrownBy {
            qna.fianceAnswer
        }.withMessage("피앙세가 답변했을 때만 피앙세 답변을 조회할 수 있습니다.")
    }

    @Nested
    inner class `isLoginUserResponseAdditionLocked 프로퍼티는` {

        @Nested
        inner class `피앙세와 연결되어 있지 않으면` {

            private val loginUser = User()
            private val qna = Qna(loginUser = loginUser)

            @Test
            fun `true이다`() {
                assertThat(qna.isLoginUserResponseAdditionLocked).isTrue()
            }
        }

        @Nested
        inner class `로그인 유저가 답변하지 않아서 피앙세의 답변이 잠겨있다면` {

            private val fiance = User()
            private val loginUser = User(fianceId = fiance.id)
            private val qna = Qna(loginUser = loginUser, fiance = fiance)

            @Test
            fun `true이다`() {
                assertThat(qna.isLoginUserResponseAdditionLocked).isTrue()
            }
        }

        @Nested
        inner class `피앙세가 답변하지 않았다면` {

            private val fiance = User()
            private val loginUser = User(fianceId = fiance.id)
            private val qna =
                Qna(loginUser = loginUser, fiance = fiance, loginUserAnswer = Answer("asdf"))

            @Test
            fun `true이다`() {
                assertThat(qna.isLoginUserResponseAdditionLocked).isTrue()
            }
        }

        @Nested
        inner class `피앙세와 연결되어 있고 로그인 유저와 피앙세가 모두 답변했다면` {

            private val fiance = User()
            private val loginUser = User(fianceId = fiance.id)
            private val qna = Qna(
                loginUser = loginUser,
                fiance = fiance,
                loginUserAnswer = Answer("asdf"),
                fianceAnswer = Answer("Asdf")
            )

            @Test
            fun `false이다`() {
                assertThat(qna.isLoginUserResponseAdditionLocked).isFalse()
            }
        }
    }

    @Test
    fun `로그인 유저의 반응 추가가 잠겨있을 때 로그인 유저가 반응했는지 조회하면 에러가 발생한다`() {
        val fiance = User()
        val loginUser = User(fianceId = fiance.id)
        val qna = Qna(loginUser = loginUser, fiance = fiance, loginUserAnswer = Answer("asdf"))

        assertThatIllegalStateException().isThrownBy {
            qna.isLoginUserReacted
        }.withMessage("로그인 유저의 답변 추가가 잠겨있지 않을 때만 로그인 유저가 반응했는지 조회할 수 있습니다.")
    }

    @Test
    fun `로그인 유저가 반응하지 않았을 때 로그인 유저의 반응을 조회하면 에러가 발생한다`() {
        val fiance = User()
        val loginUser = User(fianceId = fiance.id)
        val qna = Qna(
            loginUser = loginUser,
            fiance = fiance,
            loginUserAnswer = Answer("asdf"),
            fianceAnswer = Answer("Asdf")
        )

        assertThatIllegalStateException().isThrownBy {
            qna.loginUserResponse
        }.withMessage("로그인 유저가 반응했을 때만 로그인 유저의 반응을 조회할 수 있습니다.")
    }

    @Test
    fun `로그인 유저가 반응했을 때 로그인 유저의 반응을 조회하면 에러가 발생하지 않는다`() {
        val fiance = User()
        val loginUser = User(fianceId = fiance.id)
        val qna = Qna(
            loginUser = loginUser,
            fiance = fiance,
            loginUserAnswer = Answer("asdf"),
            fianceAnswer = Answer("Asdf"),
            loginUserResponse = Response.BETTER_KNOW
        )

        assertDoesNotThrow { qna.loginUserResponse }
    }

    @Test
    fun `로그인 유저가 반응했다면 피앙세 반응은 잠겨있지 않다`() {
        val fiance = User()
        val loginUser = User(fianceId = fiance.id)
        val qna = Qna(
            loginUser = loginUser,
            fiance = fiance,
            loginUserAnswer = Answer("asdf"),
            fianceAnswer = Answer("Asdf"),
            loginUserResponse = Response.BETTER_KNOW
        )

        assertThat(qna.isFianceResponseLocked).isFalse()
    }

    @Test
    fun `피앙세 반응이 잠겨있을 때 피앙세의 반응을 기다리고 있는지 조회하면 에러가 발생한다`() {
        val fiance = User()
        val loginUser = User(fianceId = fiance.id)
        val qna = Qna(
            loginUser = loginUser,
            fiance = fiance,
            loginUserAnswer = Answer("asdf"),
            fianceAnswer = Answer("Asdf")
        )

        assertThatIllegalStateException().isThrownBy {
            qna.isFianceResponseWaiting
        }.withMessage("피앙세의 반응이 잠겨있지 않을 때만 피앙세의 반응을 기다리는지 조회할 수 있습니다.")
    }

    @Test
    fun `피잉세 반응이 잠겨있지 않고 피앙세가 반응하지 않았다면 피앙세의 반응을 기다리고 있는 것이다`() {
        val fiance = User()
        val loginUser = User(fianceId = fiance.id)
        val qna = Qna(
            loginUser = loginUser,
            fiance = fiance,
            loginUserAnswer = Answer("asdf"),
            fianceAnswer = Answer("Asdf"),
            loginUserResponse = Response.BETTER_KNOW
        )

        assertThat(qna.isFianceResponseWaiting).isTrue()
    }

    @Test
    fun `피앙세가 반응했다면 피앙세의 반응을 기다리고 있지 않는 것이다`() {
        val fiance = User()
        val loginUser = User(fianceId = fiance.id)
        val qna = Qna(
            loginUser = loginUser,
            fiance = fiance,
            loginUserAnswer = Answer("asdf"),
            fianceAnswer = Answer("Asdf"),
            loginUserResponse = Response.BETTER_KNOW,
            fianceResponse = Response.BETTER_KNOW
        )

        assertThat(qna.isFianceResponseWaiting).isFalse()
    }

    @Test
    fun `피앙세의 반응을 기다리고 있을 때 피앙세의 반응을 조회하면 에러가 발생한다`() {
        val fiance = User()
        val loginUser = User(fianceId = fiance.id)
        val qna = Qna(
            loginUser = loginUser,
            fiance = fiance,
            loginUserAnswer = Answer("asdf"),
            fianceAnswer = Answer("Asdf"),
            loginUserResponse = Response.BETTER_KNOW
        )

        assertThatIllegalStateException().isThrownBy {
            qna.fianceResponse
        }.withMessage("피앙세의 반응을 기다리고 있는 게 아닐 때만 피앙세의 반응을 조회할 수 있습니다.")
    }

    @Nested
    inner class `isResponseResultLocked 프로퍼티는` {

        @Nested
        inner class `로그인 유저의 반응 추가가 잠겨있다면` {

            private val fiance = User()
            private val loginUser = User(fianceId = fiance.id)
            private val qna =
                Qna(loginUser = loginUser, fiance = fiance, loginUserAnswer = Answer("asdf"))

            @Test
            fun `true이다`() {
                assertThat(qna.isResponseResultLocked).isTrue()
            }
        }

        @Nested
        inner class `로그인 유저가 반응하지 않아서 피앙세 유저의 반응이 잠겨있다면` {

            private val fiance = User()
            private val loginUser = User(fianceId = fiance.id)
            private val qna = Qna(
                loginUser = loginUser,
                fiance = fiance,
                loginUserAnswer = Answer("asdf"),
                fianceAnswer = Answer("Asdf")
            )

            @Test
            fun `true이다`() {
                assertThat(qna.isResponseResultLocked).isTrue()
            }
        }

        @Nested
        inner class `피앙세 유저의 반응을 기다리고 있다면` {

            private val fiance = User()
            private val loginUser = User(fianceId = fiance.id)
            private val qna = Qna(
                loginUser = loginUser,
                fiance = fiance,
                loginUserAnswer = Answer("asdf"),
                fianceAnswer = Answer("Asdf"),
                loginUserResponse = Response.BETTER_KNOW
            )

            @Test
            fun `true이다`() {
                assertThat(qna.isResponseResultLocked).isTrue()
            }
        }

        @Nested
        inner class `로그인 유저와 피앙세 둘 다 반응했다면` {

            private val fiance = User()
            private val loginUser = User(fianceId = fiance.id)
            private val qna = Qna(
                loginUser = loginUser,
                fiance = fiance,
                loginUserAnswer = Answer("asdf"),
                fianceAnswer = Answer("Asdf"),
                loginUserResponse = Response.BETTER_KNOW,
                fianceResponse = Response.BETTER_KNOW
            )

            @Test
            fun `false이다`() {
                assertThat(qna.isResponseResultLocked).isFalse()
            }
        }
    }

    @Test
    fun `반응 결과가 잠겨있을 때 반응 결과를 조회하면 에러가 발생한다`() {
        val fiance = User()
        val loginUser = User(fianceId = fiance.id)
        val qna = Qna(
            loginUser = loginUser,
            fiance = fiance,
            loginUserAnswer = Answer("asdf"),
            fianceAnswer = Answer("Asdf"),
            loginUserResponse = Response.BETTER_KNOW
        )

        assertThatIllegalStateException().isThrownBy {
            qna.responseResult
        }.withMessage("반응 결과가 잠겨있지 않을 때만 반응 결과를 조회할 수 있습니다.")
    }

    private fun User(
        id: String = "asdf",
        name: String = "유저명",
        marriageDate: LocalDate = LocalDate.now(),
        sex: Sex = Sex.MALE,
        invitationCode: String = "asdf",
        fianceId: String? = null,
    ) = com.abloom.domain.user.model.User(
        id = id,
        name = name,
        marriageDate = marriageDate,
        sex = sex,
        invitationCode = invitationCode,
        fianceId = fianceId
    )

    private fun Qna(
        question: Question = DUMMY_QUESTION,
        createdAt: LocalDateTime = LocalDateTime.now(),
        loginUser: User,
        fiance: User? = null,
        loginUserAnswer: Answer? = null,
        fianceAnswer: Answer? = null,
        loginUserResponse: Response? = null,
        fianceResponse: Response? = null
    ) = Qna(
        question = question,
        createdAt = createdAt,
        loginUser = loginUser,
        fiance = fiance,
        _loginUserAnswer = loginUserAnswer,
        _fianceAnswer = fianceAnswer,
        _loginUserResponse = loginUserResponse,
        _fianceResponse = fianceResponse
    )

    companion object {

        private val DUMMY_QUESTION = Question(id = 1L, category = Category.CHILD, content = "asdf")
    }
}
