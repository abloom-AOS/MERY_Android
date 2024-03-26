package com.abloom.domain.qna.usecase

import com.abloom.domain.qna.model.Answer
import com.abloom.domain.qna.model.Qna
import com.abloom.domain.qna.repository.ProspectiveCoupleQnaRepository
import javax.inject.Inject

class AnswerQnaUseCase @Inject constructor(
    private val prospectiveCoupleQnaRepository: ProspectiveCoupleQnaRepository
) {

    suspend operator fun invoke(qna: Qna, answer: Answer) {
        require(!qna.isLoginUserAnswered) { "로그인 유저가 답변하지 않았을 때만 답변할 수 있습니다." }
        prospectiveCoupleQnaRepository.answerQna(qna.questionId, answer)
    }
}
