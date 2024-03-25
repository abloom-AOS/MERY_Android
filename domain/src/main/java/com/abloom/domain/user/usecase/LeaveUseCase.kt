package com.abloom.domain.user.usecase

import com.abloom.domain.user.repository.UserRepository
import javax.inject.Inject

class LeaveUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke() {
        userRepository.leave()
    }
}
