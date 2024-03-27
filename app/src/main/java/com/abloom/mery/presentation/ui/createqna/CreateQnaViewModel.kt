package com.abloom.mery.presentation.ui.createqna

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abloom.domain.question.model.Question
import com.abloom.domain.question.usecase.GetTodayRecommendationQuestionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class CreateQnaViewModel(
    getTodayRecommendationQuestionUseCase: GetTodayRecommendationQuestionUseCase
) : ViewModel() {

    val todayRecommendationQuestion: StateFlow<Question?> = getTodayRecommendationQuestionUseCase()
        .stateIn(
            initialValue = null,
            started = SharingStarted.WhileSubscribed(5_000),
            scope = viewModelScope
        )
}
