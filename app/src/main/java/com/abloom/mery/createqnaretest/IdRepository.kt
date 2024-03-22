package com.abloom.mery.createqnaretest

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class IdRepository() {

    private suspend fun requestUserInfo(): UserInfo {
        return UserInfo(1, arrayListOf("질문1","질문2","질문3","질문4","질문5"))
    }

    suspend fun requestRandomQuestion(): String {
        val userInfo = requestUserInfo()

        val questions = userInfo.questions
        return questions.random()
    }

    suspend fun requestRandomId(): Long {
        val userInfo = requestUserInfo()

        val id = userInfo.questionId
        return id
    }
}
