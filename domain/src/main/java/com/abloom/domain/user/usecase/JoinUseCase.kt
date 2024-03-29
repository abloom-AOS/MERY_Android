package com.abloom.domain.user.usecase

import com.abloom.domain.user.model.Authentication
import com.abloom.domain.user.model.Sex
import com.abloom.domain.user.repository.UserRepository
import java.time.LocalDate
import javax.inject.Inject

class JoinUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {

    /**
     * 회원가입 시 자동으로 로그인도 됩니다.
     */
    suspend operator fun invoke(
        authentication: Authentication,
        sex: Sex,
        marriageDate: LocalDate,
        name: String
    ) {
        userRepository.join(authentication, sex, marriageDate, name)
    }
}
