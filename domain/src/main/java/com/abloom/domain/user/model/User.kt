package com.abloom.domain.user.model

import java.time.LocalDate

data class User(
    val id: String,
    val name: String,
    val marriageDate: LocalDate,
    val sex: Sex,
    val invitationCode: String,
    val fianceId: String?,
) {

    val isLinkedWithFiance: Boolean = fianceId != null

    val marriageState: MarriageState
        get() = MarriageState.of(LocalDate.now(), marriageDate)
}
