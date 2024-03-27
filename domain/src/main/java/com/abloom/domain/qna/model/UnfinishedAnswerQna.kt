package com.abloom.domain.qna.model

import com.abloom.domain.question.model.Question
import com.abloom.domain.user.model.User
import java.time.LocalDate

data class UnfinishedAnswerQna(
    override val question: Question,
    override val createdAt: LocalDate,
    override val loginUser: User,
    val fiance: User,
    val loginUserAnswer: Answer? = null,
    val fianceUserAnswer: Answer? = null
) : Qna1
