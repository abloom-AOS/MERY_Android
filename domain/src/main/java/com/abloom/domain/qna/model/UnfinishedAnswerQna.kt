package com.abloom.domain.qna.model

import com.abloom.domain.question.model.Question
import com.abloom.domain.user.model.User
import java.time.LocalDateTime

data class UnfinishedAnswerQna(
    override val question: Question,
    override val createdAt: LocalDateTime,
    override val loginUser: User,
    val fiance: User,
    val loginUserAnswer: Answer? = null,
    val fianceAnswer: Answer? = null
) : Qna1
