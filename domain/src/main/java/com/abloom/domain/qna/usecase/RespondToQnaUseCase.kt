package com.abloom.domain.qna.usecase

import com.abloom.domain.qna.model.Qna1
import com.abloom.domain.qna.model.Response
import com.abloom.domain.qna.model.UnfinishedResponseQna
import com.abloom.domain.qna.repository.ProspectiveCoupleQnaRepository
import javax.inject.Inject

class RespondToQnaUseCase @Inject constructor(
    private val qnaRepository: ProspectiveCoupleQnaRepository
) {

    suspend operator fun invoke(qna: Qna1, response: Response) {
        require(qna is UnfinishedResponseQna && qna.loginUserResponse == null) { "로그인 유저가 반응을 추가할 수 없습니다." }
        qnaRepository.respondToQna(qna.question.id, response)
    }
}
