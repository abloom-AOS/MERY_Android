package com.abloom.mery.presentation.ui.createqna

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.abloom.mery.R
import com.abloom.mery.createqnaretest.CreateQnaViewModel
import com.abloom.mery.databinding.FragmentCreateQnaBinding
import com.abloom.mery.presentation.common.base.BaseFragment
import com.abloom.mery.presentation.common.view.setOnNavigationClick
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
        binding.ivEconomy.setOnClickListener { goCategoryFragment(CreateQnaCategory.ECONOMY) }
        binding.ivCommunication.setOnClickListener { goCategoryFragment(CreateQnaCategory.COMMUNICATION) }
        binding.ivValues.setOnClickListener { goCategoryFragment(CreateQnaCategory.VALUES) }
        binding.ivLife.setOnClickListener { goCategoryFragment(CreateQnaCategory.LIFE) }
        binding.ivChildren.setOnClickListener { goCategoryFragment(CreateQnaCategory.CHILDREN) }
        binding.ivFamily.setOnClickListener { goCategoryFragment(CreateQnaCategory.FAMILY) }
        binding.ivMarried.setOnClickListener { goCategoryFragment(CreateQnaCategory.MARYCOUPLE) }
        binding.ivHealth.setOnClickListener { goCategoryFragment(CreateQnaCategory.HEALTH) }
        binding.ivWedding.setOnClickListener { goCategoryFragment(CreateQnaCategory.WEDDING) }
        binding.ivFuture.setOnClickListener { goCategoryFragment(CreateQnaCategory.FUTURE) }
        binding.ivPresent.setOnClickListener { goCategoryFragment(CreateQnaCategory.PRESENT) }
        binding.ivPast.setOnClickListener { goCategoryFragment(CreateQnaCategory.PAST) }

        binding.clQuestion.setOnClickListener {
            val action = CreateQnaFragmentDirections.actionGlobalWriteAnswerFragment(id)
            findNavController().navigate(action)
        }
    }

    private fun goCategoryFragment(category: CreateQnaCategory) {
        val action = CreateQnaFragmentDirections.actionCreateQnaFragmentToCategoryFragment(category)
        findNavController().navigate(action)
    }
}
