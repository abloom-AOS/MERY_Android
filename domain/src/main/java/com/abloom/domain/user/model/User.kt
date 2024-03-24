package com.abloom.domain.user.model

import java.time.LocalDateTime

data class User(
    val id: String,
    val name: String,
    val marriageDate: LocalDateTime,
    val sex: Sex,
    val invitationCode: String,
    val fianceId: String,
)
