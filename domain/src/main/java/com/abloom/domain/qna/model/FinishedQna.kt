package com.abloom.domain.qna.model

import com.abloom.domain.question.model.Question
import com.abloom.domain.user.model.User
import java.time.LocalDate

data class FinishedQna(
    override val question: Question,
    override val createdAt: LocalDate,
    override val loginUser: User,
    val fiance: User,
    val loginUserAnswer: Answer,
    val fianceUserAnswer: Answer,
    val loginUserResponse: Response,
    val fianceResponse: Response,
) : Qna1 {

    val responseResult: ResponseResult = ResponseResult.of(loginUserResponse, fianceResponse)
}
