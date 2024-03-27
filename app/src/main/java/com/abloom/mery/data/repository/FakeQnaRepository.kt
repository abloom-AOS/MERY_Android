package com.abloom.mery.data.repository

import com.abloom.domain.qna.model.Answer
import com.abloom.domain.qna.model.Qna1
import com.abloom.domain.qna.model.Response
import com.abloom.domain.qna.model.UnfinishedResponseQna
import com.abloom.domain.qna.repository.ProspectiveCoupleQnaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import java.time.LocalDateTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeQnaRepository @Inject constructor() : ProspectiveCoupleQnaRepository {

    private val qnas = MutableStateFlow<List<Qna1>>(listOf())

    override fun getQnas(): Flow<List<Qna1>> = qnas.asStateFlow()

    override fun getQna(questionId: Long): Flow<Qna1> =
        qnas.map { it.first { it.question.id == questionId } }

    override suspend fun answerQna(questionId: Long, answer: Answer) {
        val qnas = qnas.value
        val qna = qnas.find { it.question.id == questionId }?.let {
            Qna1.create(
                question = it.question,
                createdAt = it.createdAt,
                loginUser = it.loginUser,
                fiance = FakeUserRepository.FIANCE.value,
            )
        } ?: Qna1.create(
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
            Qna1.create(
                question = qna.question,
                createdAt = qna.createdAt,
                loginUser = qna.loginUser,
                fiance = qna.fiance,
                loginUserAnswer = qna.loginUserAnswer,
                fianceAnswer = qna.fianceAnswer,
                loginUserResponse = response
            )
        }
        this.qnas.value += qna
    }
}
