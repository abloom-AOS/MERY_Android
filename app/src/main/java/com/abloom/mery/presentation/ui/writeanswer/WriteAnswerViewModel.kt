package com.abloom.mery.presentation.ui.writeanswer

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abloom.domain.qna.model.Answer
import com.abloom.domain.qna.model.Qna
import com.abloom.domain.qna.usecase.AnswerQnaUseCase
import com.abloom.domain.qna.usecase.GetQnaUseCase
import com.abloom.domain.question.model.Question
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class WriteAnswerViewModel(
    savedStateHandle: SavedStateHandle,
    getQnaUseCase: GetQnaUseCase,
    private val answerQnaUseCase: AnswerQnaUseCase
) : ViewModel() {

    @OptIn(ExperimentalCoroutinesApi::class)
    private val qna: StateFlow<Qna?> = savedStateHandle
        .getStateFlow("question_id", INVALID_QUESTION_ID)
        .filter { it != INVALID_QUESTION_ID }
        .flatMapLatest { getQnaUseCase(it) }
        .stateIn(
            initialValue = null,
            started = SharingStarted.WhileSubscribed(5_000),
            scope = viewModelScope
        )

    val question: StateFlow<Question?> = qna.filterNotNull()
        .map { it.question }
        .stateIn(
            initialValue = null,
            started = SharingStarted.WhileSubscribed(5_000),
            scope = viewModelScope
        )

    val answer = MutableStateFlow("")

    fun answerQna() = viewModelScope.launch {
        val qna = qna.value ?: return@launch
        answerQnaUseCase(qna, Answer(answer.value))
    }

    companion object {

        private const val INVALID_QUESTION_ID = -1L
    }
}
