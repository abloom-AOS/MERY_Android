package com.abloom.mery.presentation.ui.qna

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abloom.domain.qna.model.Qna
import com.abloom.domain.qna.model.Response
import com.abloom.domain.qna.usecase.GetQnaUseCase
import com.abloom.domain.qna.usecase.RespondToQnaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QnaViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getQnaUseCase: GetQnaUseCase,
    private val respondToQnaUseCase: RespondToQnaUseCase
) : ViewModel() {

    @OptIn(ExperimentalCoroutinesApi::class)
    val qna: StateFlow<Qna?> = savedStateHandle
        .getStateFlow("question_id", INVALID_QUESTION_ID)
        .filter { it != INVALID_QUESTION_ID }
        .flatMapLatest { getQnaUseCase(it) }
        .stateIn(
            initialValue = null,
            started = SharingStarted.WhileSubscribed(5_000),
            scope = viewModelScope
        )

    fun respondToQna(response: Response) = viewModelScope.launch {
        val qna = qna.value ?: return@launch
        respondToQnaUseCase(qna, response)
    }

    companion object {

        private const val INVALID_QUESTION_ID = -1L
    }
}
