package com.abloom.mery.categorytest

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
class CategoryViewModel @Inject constructor(
) : ViewModel() {

    private val repository = CategoryRepository()

    private val _questions = MutableLiveData<ArrayList<Question>>()
    val questions: LiveData<ArrayList<Question>>
        get() = _questions

    private val _isLogin = MutableLiveData<Boolean>()
    val isLogin: LiveData<Boolean>
        get() = _isLogin

    fun requestQuestion(category: String) {

        viewModelScope.launch(Dispatchers.IO) {
            val categoryQuestions = repository.requestCategoryQeustions(category)

            withContext(Dispatchers.Main) {
                _questions.value = categoryQuestions as ArrayList<Question>
            }
        }
    }

    fun isLoginCheck() {
        val check = repository.isLoginCheck()
        _isLogin.value = check
    }
}
