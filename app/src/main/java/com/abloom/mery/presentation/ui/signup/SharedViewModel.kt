package com.abloom.mery.presentation.ui.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
) : ViewModel() {

    private val _dynamicName = MutableLiveData<String>()
    val staticName
        get() = _dynamicName.value.toString()

    private val sex = MutableLiveData<Boolean>()
    fun getName() = _dynamicName
    fun setName(userName: String) {
        this._dynamicName.value = userName
    }

    fun getSex() = sex
    fun setSex(sex: Boolean) {
        this.sex.value = sex
    }
}
