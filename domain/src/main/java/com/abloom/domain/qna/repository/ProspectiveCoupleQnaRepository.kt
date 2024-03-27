package com.abloom.domain.qna.repository

import com.abloom.domain.qna.model.Answer
import com.abloom.domain.qna.model.Qna
import com.abloom.domain.qna.model.Response
import kotlinx.coroutines.flow.Flow

/**
 * 로그인 유저의 예비 부부의 문답에 대해서만 조회하고 변경할 수 있습니다.
 */
interface ProspectiveCoupleQnaRepository {

    fun getQnas(): Flow<List<Qna>>

    fun getQna(questionId: Long): Flow<Qna>

    suspend fun createQna(questionId: Long, answer: Answer)

    suspend fun answerQna(questionId: Long, answer: Answer)

    suspend fun respondToQna(questionId: Long, response: Response)
}
