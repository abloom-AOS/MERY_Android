package com.abloom.domain.qna.repository

import com.abloom.domain.qna.model.Answer
import com.abloom.domain.qna.model.Qna
import com.abloom.domain.qna.model.Response
import kotlinx.coroutines.flow.Flow

interface QnaRepository {

    fun getProspectiveCoupleQnas(): Flow<List<Qna>>

    fun getProspectiveCoupleQna(questionId: Long): Flow<Qna>

    suspend fun createProspectiveCoupleQna(questionId: Long, answer: Answer)

    suspend fun answerQna(answer: Answer)

    suspend fun reactQna(response: Response)
}
