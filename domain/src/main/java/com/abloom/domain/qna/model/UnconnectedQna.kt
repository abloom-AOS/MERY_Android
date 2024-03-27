package com.abloom.domain.qna.model

import com.abloom.domain.question.model.Question
import com.abloom.domain.user.model.User
import java.time.LocalDateTime

data class UnconnectedQna(
    override val question: Question,
    override val createdAt: LocalDateTime,
    override val loginUser: User,
    val loginUserAnswer: Answer? = null
) : Qna1
