package com.abloom.domain.question.model

data class Question(
    val id: Long,
    val category: Category,
    val content: String,
)
