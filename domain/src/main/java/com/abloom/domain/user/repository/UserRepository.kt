package com.abloom.domain.user.repository

import com.abloom.domain.user.model.Authentication
import com.abloom.domain.user.model.Sex
import com.abloom.domain.user.model.User
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface UserRepository {

    /**
     * @return 가입하지 않은 회원일 경우 false 반환
     */
    suspend fun login(authentication: Authentication): Boolean

    suspend fun join(
        authentication: Authentication,
        sex: Sex,
        marriageDate: LocalDate,
        name: String
    )

    fun getLoginUser(): Flow<User?>

    fun getFiance(): Flow<User?>

    suspend fun linkWithFiance(fianceInvitationCode: String)

    suspend fun changeLoginUserName(name: String)

    suspend fun changeLoginUserMarriageDate(marriageDate: LocalDate)

    suspend fun logout()

    suspend fun leave()
}
