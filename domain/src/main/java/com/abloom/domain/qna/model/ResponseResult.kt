package com.abloom.domain.qna.model

enum class ResponseResult {
    DOING_WELL, MORE_TALK, MORE_FIND;

    companion object {

        fun of(loginUserResponse: Response, fianceResponse: Response): ResponseResult = when {
            loginUserResponse.isPositive && fianceResponse.isPositive -> DOING_WELL
            loginUserResponse == Response.LETS_TALK || fianceResponse == Response.LETS_TALK -> MORE_TALK
            loginUserResponse == Response.LETS_FIND || fianceResponse == Response.LETS_FIND -> MORE_FIND
            else -> throw AssertionError("반응 결과를 결정하는 로직을 다시 살펴보세요.")
        }
    }
}
