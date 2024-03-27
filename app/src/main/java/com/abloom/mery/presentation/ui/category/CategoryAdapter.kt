package com.abloom.mery.presentation.ui.category

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abloom.mery.categorytest.Question

class CategoryAdapter(
    private val onCategoryItemClick: (quest: Question) -> Unit
    //    private val categoryRecyclerListener: CategoryRecyclerListener
) : RecyclerView.Adapter<CategoryViewHolder>() {

    private var questionList = arrayListOf<Question>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder.from(parent, onCategoryItemClick)
    }

    override fun getItemCount(): Int = questionList.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(questionList[position])
    }

    fun setData(movies: ArrayList<Question>) {
        this.questionList = movies
        notifyDataSetChanged()
    }
}
