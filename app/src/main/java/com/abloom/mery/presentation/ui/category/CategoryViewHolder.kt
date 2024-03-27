package com.abloom.mery.presentation.ui.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abloom.mery.categorytest.Question
import com.abloom.mery.databinding.ItemCategoryBinding

class CategoryViewHolder(
    private val binding: ItemCategoryBinding,
    private val onCategoryItemClick: (question: Question) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener {
            onCategoryItemClick(binding.question ?: return@setOnClickListener)
        }
    }

    fun bind(question: Question) {
        binding.question = question
    }

    companion object {

        fun from(
            parent: ViewGroup,
            onCategoryItemClick: (question: Question) -> Unit
        ): CategoryViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemCategoryBinding.inflate(layoutInflater, parent, false)
            return CategoryViewHolder(binding, onCategoryItemClick)
        }
    }
}
