package com.abloom.mery.presentation.ui.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
) : ViewModel() {

    private val sex = MutableLiveData<Boolean>()

    private val dynamicName = MutableLiveData<String>()
    val staticName
        get() = dynamicName.value.toString()

    private val dynamicMarryDate = MutableLiveData<MarryDate>()
    val staticMarryDate
        get() = dynamicMarryDate.value

    fun getSex() = sex
    fun setSex(sex: Boolean) {
        this.sex.value = sex
    }

    fun getName() = dynamicName
    fun setName(userName: String) {
        this.dynamicName.value = userName
    }

    fun getMarryDate() = dynamicMarryDate
    fun setMarryDate(marryDate: MarryDate) {
        this.dynamicMarryDate.value = marryDate
    }
}
