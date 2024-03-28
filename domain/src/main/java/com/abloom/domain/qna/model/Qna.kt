package com.abloom.domain.qna.model

import com.abloom.domain.question.model.Question
import com.abloom.domain.user.model.User
import java.time.LocalDateTime

sealed interface Qna {

    val question: Question
    val createdAt: LocalDateTime
    val loginUser: User

    companion object {

        fun create(
            question: Question,
            createdAt: LocalDateTime,
            loginUser: User,
            fiance: User? = null,
            loginUserAnswer: Answer? = null,
            fianceAnswer: Answer? = null,
            loginUserResponse: Response? = null,
            fianceResponse: Response? = null,
        ): Qna = when {
            fiance == null -> UnconnectedQna(
                question = question,
                createdAt = createdAt,
                loginUser = loginUser,
                loginUserAnswer = loginUserAnswer
            )

            loginUserAnswer == null || fianceAnswer == null -> UnfinishedAnswerQna(
                question = question,
                createdAt = createdAt,
                loginUser = loginUser,
                fiance = fiance,
                loginUserAnswer = loginUserAnswer,
                fianceAnswer = fianceAnswer
            )

            loginUserResponse == null || fianceResponse == null -> UnfinishedResponseQna(
                question = question,
                createdAt = createdAt,
                loginUser = loginUser,
                fiance = fiance,
                loginUserAnswer = loginUserAnswer,
                fianceAnswer = fianceAnswer,
                loginUserResponse = loginUserResponse,
                fianceResponse = fianceResponse
            )

            else -> FinishedQna(
                question = question,
                createdAt = createdAt,
                loginUser = loginUser,
                fiance = fiance,
                loginUserAnswer = loginUserAnswer,
                fianceAnswer = fianceAnswer,
                loginUserResponse = loginUserResponse,
                fianceResponse = fianceResponse
            )
        }
    }
}








