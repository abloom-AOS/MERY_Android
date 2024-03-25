package com.abloom.domain.qna.model

enum class Response(val isPositive: Boolean) {
    GOOD(isPositive = true),
    BETTER_KNOW(isPositive = true),
    LETS_TALK(isPositive = false),
    LETS_FIND(isPositive = false)
}
