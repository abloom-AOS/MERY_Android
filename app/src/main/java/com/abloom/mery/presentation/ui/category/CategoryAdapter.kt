package com.abloom.mery.presentation.ui.category

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abloom.domain.question.model.Question

class CategoryAdapter(
    private val onCategoryItemClick: (quest: Question) -> Unit
) : RecyclerView.Adapter<CategoryViewHolder>() {

    private var questionList = listOf<Question>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder.from(parent, onCategoryItemClick)
    }

    override fun getItemCount(): Int = questionList.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(questionList[position])
    }

    fun setData(movies: List<Question>) {
        this.questionList = movies
        notifyDataSetChanged()
    }
}
