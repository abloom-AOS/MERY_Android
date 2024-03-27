package com.abloom.mery.data.repository

import com.abloom.domain.qna.model.Answer
import com.abloom.domain.qna.model.Qna
import com.abloom.domain.qna.model.Response
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

    private val qnas = MutableStateFlow<List<Qna>>(listOf())

    override fun getQnas(): Flow<List<Qna>> = qnas.asStateFlow()

    override fun getQna(questionId: Long): Flow<Qna> =
        qnas.map { it.first { it.questionId == questionId } }

    override suspend fun answerQna(questionId: Long, answer: Answer) {
        val qnas = qnas.value
        val qna = qnas.find { it.questionId == questionId }?.copy(_loginUserAnswer = answer)
            ?: Qna(
                question = FakeQuestionRepository.QUESTIONS.value.first { it.id == questionId },
                createdAt = LocalDateTime.now(),
                loginUser = FakeUserRepository.LOGIN_USER.value!!,
                fiance = FakeUserRepository.FIANCE.value,
                _loginUserAnswer = answer,
            )
        this.qnas.value += qna
    }

    override suspend fun respondToQna(questionId: Long, response: Response) {
        val qnas = qnas.value
        val qna = qnas.first { it.questionId == questionId }.copy(_loginUserResponse = response)
        this.qnas.value += qna
    }
}
