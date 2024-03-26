package com.abloom.domain.qna.repository

import com.abloom.domain.qna.model.Answer
import com.abloom.domain.qna.model.Qna
import com.abloom.domain.qna.model.Response
import kotlinx.coroutines.flow.Flow

interface ProspectiveCoupleQnaRepository {

    fun getQnas(): Flow<List<Qna>>

    fun getQna(questionId: Long): Flow<Qna>

    suspend fun createQna(questionId: Long, answer: Answer)

    suspend fun answerQna(questionId: Long, answer: Answer)

    suspend fun respondToQna(questionId: Long, response: Response)
}
