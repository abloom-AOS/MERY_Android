package com.abloom.mery.categorytest

class CategoryRepository {

    private fun requestQuestions(): List<Question> = FakeQuestions().questions

    //해당 데이터를 가져오는 로직이 suspend라는 가정하에 데이터 filter
    suspend fun requestCategoryQeustions(category: String): List<Question> {
        val fakeQuestions = requestQuestions()
        val filteredQuestions = fakeQuestions.filter {
            it.category == category
        }
        return filteredQuestions
    }

    fun isLoginCheck(): Boolean {
        val isLogin = true
        return isLogin
    }
}
