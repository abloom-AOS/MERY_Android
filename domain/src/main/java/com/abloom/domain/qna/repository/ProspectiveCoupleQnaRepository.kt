package com.abloom.domain.qna.repository

import com.abloom.domain.qna.model.Answer
import com.abloom.domain.qna.model.Qna
import com.abloom.domain.qna.model.Response
import kotlinx.coroutines.flow.Flow

interface ProspectiveCoupleQnaRepository {

    fun getProspectiveCoupleQnas(): Flow<List<Qna>>

    fun getProspectiveCoupleQna(questionId: Long): Flow<Qna>

    suspend fun createProspectiveCoupleQna(questionId: Long, answer: Answer)

    suspend fun answerProspectiveCoupleQna(questionId: Long, answer: Answer)

    suspend fun respondToProspectiveCoupleQna(questionId: Long, response: Response)
}
