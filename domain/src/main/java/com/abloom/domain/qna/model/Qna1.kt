package com.abloom.domain.qna.model

import com.abloom.domain.question.model.Question
import com.abloom.domain.user.model.User
import java.time.LocalDate

sealed interface Qna1 {

    val question: Question
    val createdAt: LocalDate
    val loginUser: User

    companion object {

        fun create(
            question: Question,
            createdAt: LocalDate,
            loginUser: User,
            fiance: User? = null,
            loginUserAnswer: Answer? = null,
            fianceUserAnswer: Answer? = null,
            loginUserResponse: Response? = null,
            fianceResponse: Response? = null,
        ): Qna1 = when {
            fiance == null -> UnconnectedQna(
                question = question,
                createdAt = createdAt,
                loginUser = loginUser,
                loginUserAnswer = loginUserAnswer
            )

            loginUserAnswer == null || fianceUserAnswer == null -> UnfinishedAnswerQna(
                question = question,
                createdAt = createdAt,
                loginUser = loginUser,
                fiance = fiance,
                loginUserAnswer = loginUserAnswer,
                fianceUserAnswer = fianceUserAnswer
            )

            loginUserResponse == null || fianceResponse == null -> UnfinishedResponseQna(
                question = question,
                createdAt = createdAt,
                loginUser = loginUser,
                fiance = fiance,
                loginUserAnswer = loginUserAnswer,
                fianceUserAnswer = fianceUserAnswer,
                loginUserResponse = loginUserResponse,
                fianceResponse = fianceResponse
            )

            else -> FinishedQna(
                question = question,
                createdAt = createdAt,
                loginUser = loginUser,
                fiance = fiance,
                loginUserAnswer = loginUserAnswer,
                fianceUserAnswer = fianceUserAnswer,
                loginUserResponse = loginUserResponse,
                fianceResponse = fianceResponse
            )
        }
    }
}








