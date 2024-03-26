package com.abloom.mery.presentation.ui.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abloom.mery.categorytest.Question
import com.abloom.mery.databinding.ItemCategoryBinding

class CategoryViewHolder(
    private val binding: ItemCategoryBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(categoryRecyclerListener: CategoryRecyclerListener, question: Question) {
        binding.tv.text = question.content
        binding.questionItem.setOnClickListener {
            categoryRecyclerListener.onCategoryItemClick(question)
        }
    }

    companion object{
        fun from(parent: ViewGroup): CategoryViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemCategoryBinding.inflate(layoutInflater, parent, false)
            return CategoryViewHolder(binding)
        }
    }

}
