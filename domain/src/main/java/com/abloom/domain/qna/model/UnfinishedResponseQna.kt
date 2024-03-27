package com.abloom.domain.qna.model

import com.abloom.domain.question.model.Question
import com.abloom.domain.user.model.User
import java.time.LocalDateTime

data class UnfinishedResponseQna(
    override val question: Question,
    override val createdAt: LocalDateTime,
    override val loginUser: User,
    val fiance: User,
    val loginUserAnswer: Answer,
    val fianceAnswer: Answer,
    val loginUserResponse: Response? = null,
    val fianceResponse: Response? = null,
) : Qna
