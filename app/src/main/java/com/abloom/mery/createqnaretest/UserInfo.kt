package com.abloom.mery.createqnaretest

data class UserInfo(
    val questionId: Long,
    val questions : ArrayList<String> = arrayListOf("1","2","3")
)
