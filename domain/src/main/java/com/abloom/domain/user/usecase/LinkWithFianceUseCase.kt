package com.abloom.domain.user.usecase

import com.abloom.domain.user.repository.UserRepository
import javax.inject.Inject

class LinkWithFianceUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(fianceInvitationCode: String) {
        userRepository.linkWithFiance(fianceInvitationCode)
    }
}
