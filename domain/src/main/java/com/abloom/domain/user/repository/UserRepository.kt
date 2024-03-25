package com.abloom.domain.user.repository

import com.abloom.domain.user.model.Sex
import com.abloom.domain.user.model.User
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface UserRepository {

    /**
     * 추후 사용자의 외부 인증 서버(카카오, 구글, 애플)에서의 식별자를 매개변수로 추가할 수 있음
     */
    suspend fun join(sex: Sex, marriageDate: LocalDate, name: String)

    fun getLoginUser(): Flow<User?>

    fun getFiance(): Flow<User?>

    suspend fun linkWithFiance(fianceInvitationCode: String)

    suspend fun changeLoginUserName(name: String)

    suspend fun changeLoginUserMarriageDate(marriageDate: LocalDate)

    suspend fun logout()

    suspend fun leave()
}
