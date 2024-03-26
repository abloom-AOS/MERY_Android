package com.abloom.mery.presentation.ui.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abloom.mery.categorytest.Question
import com.abloom.mery.databinding.ItemCategoryBinding

class CategoryAdapter(
    private val categoryRecyclerListener: CategoryRecyclerListener
) : RecyclerView.Adapter<CategoryViewHolder>() {

    private var questionList = arrayListOf<Question>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val itemBinding =
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return questionList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categoryRecyclerListener, questionList[position])
    }

    fun setData(movies: ArrayList<Question>) {
        this.questionList = movies
        notifyDataSetChanged()
    }
}
