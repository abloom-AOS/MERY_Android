package com.abloom.domain.qna.usecase

import com.abloom.domain.qna.model.Answer
import com.abloom.domain.qna.repository.ProspectiveCoupleQnaRepository
import javax.inject.Inject

class CreateQnaUseCase @Inject constructor(
    private val qnaRepository: ProspectiveCoupleQnaRepository
){

    /**
     * 연인이 질문에 대해 문답을 생성하지 않았을 때만 사용해야 합니다.
     */
    suspend operator fun invoke(questionId: Long, answer: Answer) {
        qnaRepository.createQna(questionId, answer)
    }
}
