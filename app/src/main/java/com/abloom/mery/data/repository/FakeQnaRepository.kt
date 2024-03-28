package com.abloom.mery.data.repository

import com.abloom.domain.qna.model.Answer
import com.abloom.domain.qna.model.Qna
import com.abloom.domain.qna.model.Response
import com.abloom.domain.qna.model.UnfinishedResponseQna
import com.abloom.domain.qna.repository.ProspectiveCoupleQnaRepository
import com.abloom.domain.question.model.Category
import com.abloom.domain.question.model.Question
import com.abloom.domain.user.model.Sex
import com.abloom.domain.user.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeQnaRepository @Inject constructor() : ProspectiveCoupleQnaRepository {

    private val qnas = MutableStateFlow(
        listOf(
            Qna.create(
                question = Question(
                    id = 1L,
                    category = Category.FINANCE,
                    content = "돈을 어떤식으로 관리하고 있어? 저축, 투자, 소비 등의 비율이 어떻게 돼?",
                ),
                createdAt = LocalDateTime.now(),
                loginUser = User(
                    id = "asdf",
                    name = "이지훈",
                    marriageDate = LocalDate.now(),
                    sex = Sex.MALE,
                    invitationCode = "asdf",
                    fianceId = null,
                ),
                loginUserAnswer = Answer("나는 매달 모든 수익을 급여통장에 옮겨두고 있어. 이 중 50은 소비통장으로, 30은 저축통장으로, 20은 투자통장으로 옮겨서 각각 관리하고 있어. 주로 ISA를 이용해 저축을 하고 있고, 주식에 투자를 많이 하고 있는 편이야.")
            ),
            Qna.create(
                question = Question(
                    id = 2L,
                    category = Category.FINANCE,
                    content = "돈을 어떤식으로 관리하고 있어? 저축, 투자, 소비 등의 비율이 어떻게 돼?",
                ),
                createdAt = LocalDateTime.now(),
                loginUser = User(
                    id = "asdf",
                    name = "이지훈",
                    marriageDate = LocalDate.now(),
                    sex = Sex.MALE,
                    invitationCode = "asdf",
                    fianceId = "qwer",
                ),
                fiance = User(
                    id = "qwer",
                    name = "최지은",
                    marriageDate = LocalDate.now(),
                    sex = Sex.FEMALE,
                    invitationCode = "asdf",
                    fianceId = "asdf",
                ),
                fianceAnswer = Answer("나는 소비가 우선이어서 막 체계적인 관리는 못하고 있는 것 같아 ㅎㅎ..... 대신 아껴서 소비하려고 하는 편이고 소비를 한 다음에 남는 돈을 저축도 하고 투자도 하고 있어!"),
            ),
            Qna.create(
                question = Question(
                    id = 3L,
                    category = Category.FINANCE,
                    content = "돈을 어떤식으로 관리하고 있어? 저축, 투자, 소비 등의 비율이 어떻게 돼?",
                ),
                createdAt = LocalDateTime.now(),
                loginUser = User(
                    id = "asdf",
                    name = "이지훈",
                    marriageDate = LocalDate.now(),
                    sex = Sex.MALE,
                    invitationCode = "asdf",
                    fianceId = "qwer",
                ),
                fiance = User(
                    id = "qwer",
                    name = "최지은",
                    marriageDate = LocalDate.now(),
                    sex = Sex.FEMALE,
                    invitationCode = "asdf",
                    fianceId = "asdf",
                ),
                loginUserAnswer = Answer("나는 매달 모든 수익을 급여통장에 옮겨두고 있어. 이 중 50은 소비통장으로, 30은 저축통장으로, 20은 투자통장으로 옮겨서 각각 관리하고 있어. 주로 ISA를 이용해 저축을 하고 있고, 주식에 투자를 많이 하고 있는 편이야."),
                fianceAnswer = Answer("나는 소비가 우선이어서 막 체계적인 관리는 못하고 있는 것 같아 ㅎㅎ..... 대신 아껴서 소비하려고 하는 편이고 소비를 한 다음에 남는 돈을 저축도 하고 투자도 하고 있어!"),
            ),
            Qna.create(
                question = Question(
                    id = 4L,
                    category = Category.FINANCE,
                    content = "돈을 어떤식으로 관리하고 있어? 저축, 투자, 소비 등의 비율이 어떻게 돼?",
                ),
                createdAt = LocalDateTime.now(),
                loginUser = User(
                    id = "asdf",
                    name = "이지훈",
                    marriageDate = LocalDate.now(),
                    sex = Sex.MALE,
                    invitationCode = "asdf",
                    fianceId = "qwer",
                ),
                fiance = User(
                    id = "qwer",
                    name = "최지은",
                    marriageDate = LocalDate.now(),
                    sex = Sex.FEMALE,
                    invitationCode = "asdf",
                    fianceId = "asdf",
                ),
                loginUserAnswer = Answer("나는 매달 모든 수익을 급여통장에 옮겨두고 있어. 이 중 50은 소비통장으로, 30은 저축통장으로, 20은 투자통장으로 옮겨서 각각 관리하고 있어. 주로 ISA를 이용해 저축을 하고 있고, 주식에 투자를 많이 하고 있는 편이야."),
                fianceAnswer = Answer("나는 소비가 우선이어서 막 체계적인 관리는 못하고 있는 것 같아 ㅎㅎ..... 대신 아껴서 소비하려고 하는 편이고 소비를 한 다음에 남는 돈을 저축도 하고 투자도 하고 있어!"),
                loginUserResponse = Response.BETTER_KNOW,
            ),
            Qna.create(
                question = Question(
                    id = 5L,
                    category = Category.FINANCE,
                    content = "돈을 어떤식으로 관리하고 있어? 저축, 투자, 소비 등의 비율이 어떻게 돼?",
                ),
                createdAt = LocalDateTime.now(),
                loginUser = User(
                    id = "asdf",
                    name = "이지훈",
                    marriageDate = LocalDate.now(),
                    sex = Sex.MALE,
                    invitationCode = "asdf",
                    fianceId = "qwer",
                ),
                fiance = User(
                    id = "qwer",
                    name = "최지은",
                    marriageDate = LocalDate.now(),
                    sex = Sex.FEMALE,
                    invitationCode = "asdf",
                    fianceId = "asdf",
                ),
                loginUserAnswer = Answer("나는 매달 모든 수익을 급여통장에 옮겨두고 있어. 이 중 50은 소비통장으로, 30은 저축통장으로, 20은 투자통장으로 옮겨서 각각 관리하고 있어. 주로 ISA를 이용해 저축을 하고 있고, 주식에 투자를 많이 하고 있는 편이야."),
                fianceAnswer = Answer("나는 소비가 우선이어서 막 체계적인 관리는 못하고 있는 것 같아 ㅎㅎ..... 대신 아껴서 소비하려고 하는 편이고 소비를 한 다음에 남는 돈을 저축도 하고 투자도 하고 있어!"),
                fianceResponse = Response.BETTER_KNOW,
            ),
            Qna.create(
                question = Question(
                    id = 6L,
                    category = Category.FINANCE,
                    content = "돈을 어떤식으로 관리하고 있어? 저축, 투자, 소비 등의 비율이 어떻게 돼?",
                ),
                createdAt = LocalDateTime.now(),
                loginUser = User(
                    id = "asdf",
                    name = "이지훈",
                    marriageDate = LocalDate.now(),
                    sex = Sex.MALE,
                    invitationCode = "asdf",
                    fianceId = "qwer",
                ),
                fiance = User(
                    id = "qwer",
                    name = "최지은",
                    marriageDate = LocalDate.now(),
                    sex = Sex.FEMALE,
                    invitationCode = "asdf",
                    fianceId = "asdf",
                ),
                loginUserAnswer = Answer("나는 매달 모든 수익을 급여통장에 옮겨두고 있어. 이 중 50은 소비통장으로, 30은 저축통장으로, 20은 투자통장으로 옮겨서 각각 관리하고 있어. 주로 ISA를 이용해 저축을 하고 있고, 주식에 투자를 많이 하고 있는 편이야."),
                fianceAnswer = Answer("나는 소비가 우선이어서 막 체계적인 관리는 못하고 있는 것 같아 ㅎㅎ..... 대신 아껴서 소비하려고 하는 편이고 소비를 한 다음에 남는 돈을 저축도 하고 투자도 하고 있어!"),
                loginUserResponse = Response.BETTER_KNOW,
                fianceResponse = Response.LETS_TALK,
            ),
            Qna.create(
                question = Question(
                    id = 7L,
                    category = Category.FINANCE,
                    content = "돈을 어떤식으로 관리하고 있어? 저축, 투자, 소비 등의 비율이 어떻게 돼?",
                ),
                createdAt = LocalDateTime.now(),
                loginUser = User(
                    id = "asdf",
                    name = "이지훈",
                    marriageDate = LocalDate.now(),
                    sex = Sex.MALE,
                    invitationCode = "asdf",
                    fianceId = "qwer",
                ),
                fiance = User(
                    id = "qwer",
                    name = "최지은",
                    marriageDate = LocalDate.now(),
                    sex = Sex.FEMALE,
                    invitationCode = "asdf",
                    fianceId = "asdf",
                ),
                loginUserAnswer = Answer("나는 매달 모든 수익을 급여통장에 옮겨두고 있어. 이 중 50은 소비통장으로, 30은 저축통장으로, 20은 투자통장으로 옮겨서 각각 관리하고 있어. 주로 ISA를 이용해 저축을 하고 있고, 주식에 투자를 많이 하고 있는 편이야."),
                fianceAnswer = Answer("나는 소비가 우선이어서 막 체계적인 관리는 못하고 있는 것 같아 ㅎㅎ..... 대신 아껴서 소비하려고 하는 편이고 소비를 한 다음에 남는 돈을 저축도 하고 투자도 하고 있어!"),
                loginUserResponse = Response.BETTER_KNOW,
                fianceResponse = Response.GOOD,
            ),
            Qna.create(
                question = Question(
                    id = 8L,
                    category = Category.FINANCE,
                    content = "돈을 어떤식으로 관리하고 있어? 저축, 투자, 소비 등의 비율이 어떻게 돼?",
                ),
                createdAt = LocalDateTime.now(),
                loginUser = User(
                    id = "asdf",
                    name = "이지훈",
                    marriageDate = LocalDate.now(),
                    sex = Sex.MALE,
                    invitationCode = "asdf",
                    fianceId = "qwer",
                ),
                fiance = User(
                    id = "qwer",
                    name = "최지은",
                    marriageDate = LocalDate.now(),
                    sex = Sex.FEMALE,
                    invitationCode = "asdf",
                    fianceId = "asdf",
                ),
                loginUserAnswer = Answer("나는 매달 모든 수익을 급여통장에 옮겨두고 있어. 이 중 50은 소비통장으로, 30은 저축통장으로, 20은 투자통장으로 옮겨서 각각 관리하고 있어. 주로 ISA를 이용해 저축을 하고 있고, 주식에 투자를 많이 하고 있는 편이야."),
            )
        )
    )

    override fun getQnas(): Flow<List<Qna>> = qnas.asStateFlow()

    override fun getQna(questionId: Long): Flow<Qna> =
        qnas.map { it.first { it.question.id == questionId } }

    override suspend fun answerQna(questionId: Long, answer: Answer) {
        val qnas = qnas.value
        val qna = qnas.find { it.question.id == questionId }?.let {
            Qna.create(
                question = it.question,
                createdAt = it.createdAt,
                loginUser = it.loginUser,
                fiance = FakeUserRepository.FIANCE.value,
            )
        } ?: Qna.create(
            question = FakeQuestionRepository.QUESTIONS.value.first { it.id == questionId },
            createdAt = LocalDateTime.now(),
            loginUser = FakeUserRepository.LOGIN_USER.value!!,
            fiance = FakeUserRepository.FIANCE.value,
            loginUserAnswer = answer,
        )
        this.qnas.value += qna
    }

    override suspend fun respondToQna(questionId: Long, response: Response) {
        val qnas = qnas.value
        val qna = qnas.first { it.question.id == questionId }.let { qna ->
            val qna = qna as UnfinishedResponseQna
            Qna.create(
                question = qna.question,
                createdAt = qna.createdAt,
                loginUser = qna.loginUser,
                fiance = qna.fiance,
                loginUserAnswer = qna.loginUserAnswer,
                fianceAnswer = qna.fianceAnswer,
                loginUserResponse = response
            )
        }
        this.qnas.value = this.qnas.value.filter { it.question.id != qna.question.id }
        this.qnas.value += qna
    }
}
