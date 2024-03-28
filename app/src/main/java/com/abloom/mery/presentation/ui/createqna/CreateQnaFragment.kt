package com.abloom.mery.presentation.ui.createqna

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.abloom.mery.R
import com.abloom.mery.createqnaretest.CreateQnaViewModel
import com.abloom.mery.databinding.FragmentCreateQnaBinding
import com.abloom.mery.presentation.common.base.BaseFragment
import com.abloom.mery.presentation.common.view.setOnNavigationClick
import com.abloom.mery.presentation.ui.category.CategoryArgs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateQnaFragment : BaseFragment<FragmentCreateQnaBinding>(R.layout.fragment_create_qna) {

    //더미 데이터
    private var id = 0L
    private val viewModel: CreateQnaViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.appbarCreateQna.setOnNavigationClick {
            findNavController().popBackStack()
        }
        viewModel.requestRandomQuestion()
        viewModel.requestRandomId()
        setupTodayQuestionText()
        observeId()
        initListener()
    }

    private fun setupTodayQuestionText() {
        viewModel.randomQuestion.observe(viewLifecycleOwner) {
            binding.tvTodayQuestion.text = it
        }
    }

    private fun observeId() {
        viewModel.id.observe(viewLifecycleOwner) {
            id = it
        }
    }

    private fun initListener() {
        binding.ivEconomy.setOnClickListener { goCategoryFragment(CategoryArgs.FINANCE) }
        binding.ivCommunication.setOnClickListener { goCategoryFragment(CategoryArgs.COMMUNICATION) }
        binding.ivValues.setOnClickListener { goCategoryFragment(CategoryArgs.VALUES) }
        binding.ivLife.setOnClickListener { goCategoryFragment(CategoryArgs.LIFESTYLE) }
        binding.ivChildren.setOnClickListener { goCategoryFragment(CategoryArgs.CHILDREN) }
        binding.ivFamily.setOnClickListener { goCategoryFragment(CategoryArgs.FAMILY) }
        binding.ivMarried.setOnClickListener { goCategoryFragment(CategoryArgs.SEX) }
        binding.ivHealth.setOnClickListener { goCategoryFragment(CategoryArgs.HEALTH) }
        binding.ivWedding.setOnClickListener { goCategoryFragment(CategoryArgs.WEDDING) }
        binding.ivFuture.setOnClickListener { goCategoryFragment(CategoryArgs.FUTURE) }
        binding.ivPresent.setOnClickListener { goCategoryFragment(CategoryArgs.PRESENT) }
        binding.ivPast.setOnClickListener { goCategoryFragment(CategoryArgs.PAST) }

        binding.clQuestion.setOnClickListener {
            val action = CreateQnaFragmentDirections.actionGlobalWriteAnswerFragment(id)
            findNavController().navigate(action)
        }
    }

    private fun goCategoryFragment(category: CategoryArgs) {
        val action = CreateQnaFragmentDirections.actionCreateQnaFragmentToCategoryFragment(category)
        findNavController().navigate(action)
    }
}
