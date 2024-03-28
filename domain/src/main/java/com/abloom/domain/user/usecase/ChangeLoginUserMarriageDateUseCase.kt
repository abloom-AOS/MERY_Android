package com.abloom.domain.user.usecase

import com.abloom.domain.user.repository.UserRepository
import java.time.LocalDate
import javax.inject.Inject

class ChangeLoginUserMarriageDateUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(marriageDate: LocalDate) {
        userRepository.changeLoginUserMarriageDate(marriageDate)
    }
}
