package com.abloom.domain.user.usecase

import com.abloom.domain.user.model.Sex
import com.abloom.domain.user.repository.UserRepository
import java.time.LocalDate
import javax.inject.Inject

class JoinUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {

    /**
     * 추후 사용자의 외부 인증 서버(카카오, 구글, 애플)에서의 식벌자를 매개변수로 추가할 수 있음
     */
    suspend operator fun invoke(sex: Sex, marriageDate: LocalDate, name: String) {
        userRepository.join(sex, marriageDate, name)
    }
}
