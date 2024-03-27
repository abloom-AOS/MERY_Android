package com.abloom.domain.user.usecase

import com.abloom.domain.user.model.Authentication
import com.abloom.domain.user.repository.UserRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    /**
     * @return 가입하지 않은 회원일 경우 false 반환
     */
    suspend operator fun invoke(authentication: Authentication) =
        userRepository.login(authentication)
}
