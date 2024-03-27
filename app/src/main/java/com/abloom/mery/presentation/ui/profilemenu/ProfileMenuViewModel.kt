package com.abloom.mery.presentation.ui.profilemenu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abloom.domain.user.model.User
import com.abloom.domain.user.usecase.ChangeLoginUserMarriageDateUseCase
import com.abloom.domain.user.usecase.ChangeLoginUserNameUseCase
import com.abloom.domain.user.usecase.GetLoginUserUseCase
import com.abloom.domain.user.usecase.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate

@HiltViewModel
class ProfileMenuViewModel(
    getLoginUserUseCase: GetLoginUserUseCase,
    private val changeLoginUserNameUseCase: ChangeLoginUserNameUseCase,
    private val changeLoginUserMarriageDateUseCase: ChangeLoginUserMarriageDateUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    val loginUser: StateFlow<User?> = getLoginUserUseCase().stateIn(
        initialValue = null,
        started = SharingStarted.WhileSubscribed(5_000),
        scope = viewModelScope
    )

    fun changeName(name: String) = viewModelScope.launch {
        changeLoginUserNameUseCase(name)
    }

    fun changeMarriageDate(marriageDate: LocalDate) = viewModelScope.launch {
        changeLoginUserMarriageDateUseCase(marriageDate)
    }

    fun logout() = viewModelScope.launch { logoutUseCase() }
}
