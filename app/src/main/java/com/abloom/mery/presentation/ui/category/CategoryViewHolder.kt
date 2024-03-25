package com.abloom.mery.presentation.ui.category

import androidx.recyclerview.widget.RecyclerView
import com.abloom.mery.categorytest.Question
import com.abloom.mery.databinding.ItemCategoryBinding

class CategoryViewHolder(
    private val binding: ItemCategoryBinding
): RecyclerView.ViewHolder(binding.root){

    fun bind(categoryRecyclerListener: CategoryRecyclerListener, question: Question) {
        binding.tv.text = question.content
        binding.questionItem.setOnClickListener {
            categoryRecyclerListener.onCategoryItemClick(question)
        }
    }
}
