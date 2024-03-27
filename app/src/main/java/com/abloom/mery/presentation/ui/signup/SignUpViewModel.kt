package com.abloom.mery.presentation.ui.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abloom.domain.user.model.Authentication
import com.abloom.domain.user.model.Sex
import com.abloom.domain.user.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

@HiltViewModel
class SignUpViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _selectedSex = MutableStateFlow<Sex?>(null)
    val selectedSex = _selectedSex.asStateFlow()

    private val _selectedMarriageDate = MutableStateFlow<LocalDate>(LocalDate.now())
    val selectedMarriage = _selectedMarriageDate.asStateFlow()

    val name = MutableStateFlow("")

    fun selectSex(sex: Sex) {
        _selectedSex.value = sex
    }

    fun selectMarriageDate(marriageDate: LocalDate) {
        _selectedMarriageDate.value = marriageDate
    }

    fun join(authentication: Authentication) = viewModelScope.launch {
        val selectedSex = _selectedSex.value ?: return@launch
        userRepository.join(
            authentication = authentication,
            sex = selectedSex,
            marriageDate = _selectedMarriageDate.value,
            name = name.value
        )
    }
}
