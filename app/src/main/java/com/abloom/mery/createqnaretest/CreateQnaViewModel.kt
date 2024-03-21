package com.abloom.mery.createqnaretest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CreateQnaViewModel @Inject constructor(
):ViewModel() {

    private val repository = IdRepository()


    private val _randomQuestion = MutableLiveData<String>()
    val randomQuestion: LiveData<String>
        get() = _randomQuestion

    private val _id = MutableLiveData<Long>()
    val id: LiveData<Long>
        get() = _id

    fun requestRandomQuestion(){
        viewModelScope.launch(Dispatchers.IO) {
            val rQuesttion = repository.requestRandomQuestion()

            withContext(Dispatchers.Main){
                _randomQuestion.value = rQuesttion
            }
        }
    }

    fun requestRandomId(){
        viewModelScope.launch(Dispatchers.IO) {
            val id = repository.requestRandomId()

            withContext(Dispatchers.Main){
                _id.value = id
            }
        }
    }

}
