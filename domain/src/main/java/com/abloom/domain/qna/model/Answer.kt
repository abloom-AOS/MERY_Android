package com.abloom.domain.qna.model

@JvmInline
value class Answer(val value: String) {

    init {
        require(value.length <= ANSWER_MAX_LENGTH) { "답변의 최대 길이는 ${ANSWER_MAX_LENGTH}자 입니다." }
    }

    companion object {

        const val ANSWER_MAX_LENGTH = 150
    }
}
