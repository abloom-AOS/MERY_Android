package com.abloom.domain.qna.usecase

import com.abloom.domain.qna.model.Answer
import com.abloom.domain.qna.model.Qna
import com.abloom.domain.qna.repository.ProspectiveCoupleQnaRepository
import javax.inject.Inject

class AnswerQnaUseCase @Inject constructor(
    private val prospectiveCoupleQnaRepository: ProspectiveCoupleQnaRepository
) {

    /**
     * 만약 해당 질문에 대한 문답이 없으면 문답을 생성합니다.
     * 만약 이미 질문에 대한 대답이 있으면 대답을 갱신하지 않고 새 대답을 무시합니다.
     */
    suspend operator fun invoke(questionId: Long, answer: Answer) {
        prospectiveCoupleQnaRepository.answerQna(questionId, answer)
    }
}
