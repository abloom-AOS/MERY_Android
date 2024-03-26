package com.abloom.mery.presentation.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
) : ViewModel() {

    private val liveText = MutableLiveData<String>()

    fun getText(): LiveData<String> = liveText

    fun setText(text: String) {
        liveText.value = text
    }

}
