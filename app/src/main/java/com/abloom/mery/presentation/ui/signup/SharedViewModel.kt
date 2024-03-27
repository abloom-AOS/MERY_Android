package com.abloom.mery.presentation.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
) : ViewModel() {

    private val name = MutableLiveData<String>()
    private val sex = MutableLiveData<Boolean>()
    fun getName(): LiveData<String> = name
    fun setName(userName: String) {
        this.name.value = userName
    }

    fun getSex(): LiveData<Boolean> = sex
    fun setSex(sex: Boolean) {
        this.sex.value = sex
    }
}
