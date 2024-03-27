package com.abloom.mery.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abloom.domain.qna.model.Qna
import com.abloom.domain.qna.repository.ProspectiveCoupleQnaRepository
import com.abloom.domain.user.model.Authentication
import com.abloom.domain.user.model.User
import com.abloom.domain.user.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel(
    private val userRepository: UserRepository,
    qnaRepository: ProspectiveCoupleQnaRepository
) : ViewModel() {

    val loginUser: StateFlow<User?> = userRepository.getLoginUser()
        .stateIn(
            initialValue = null,
            started = SharingStarted.WhileSubscribed(5_000),
            scope = viewModelScope
        )

    val isLogin: StateFlow<Boolean> = loginUser.map { it == null }.stateIn(
        initialValue = false,
        started = SharingStarted.WhileSubscribed(5_000),
        scope = viewModelScope
    )

    val qnas: StateFlow<List<Qna>> = qnaRepository.getQnas()
        .stateIn(
            initialValue = listOf(),
            started = SharingStarted.WhileSubscribed(5_000),
            scope = viewModelScope
        )

    private val _event = MutableSharedFlow<HomeEvent>()
    val event: SharedFlow<HomeEvent> = _event.asSharedFlow()

    fun login(authentication: Authentication) = viewModelScope.launch {
        val isLoginSuccess = userRepository.login(authentication)
        if (!isLoginSuccess) _event.emit(HomeEvent.LoginFail)
    }
}
