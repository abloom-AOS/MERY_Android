package com.abloom.mery.presentation.ui.createqna

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
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
        initQaIdAndQuestion()
        initListener()
    }

    private fun initQaIdAndQuestion() {
        viewModel.randomQuestion.observe(viewLifecycleOwner) {
            binding.tvTodayQuestion.text = it
        }
        viewModel.id.observe(viewLifecycleOwner) {
            id = it
        }
    }

    private fun initListener() {
        binding.ivEconomy.setOnClickListener { goCategoryFragment(1) }
        binding.ivCommunication.setOnClickListener { goCategoryFragment(2) }
        binding.ivValues.setOnClickListener { goCategoryFragment(3) }
        binding.ivLife.setOnClickListener { goCategoryFragment(4) }
        binding.ivChildren.setOnClickListener { goCategoryFragment(5) }
        binding.ivFamily.setOnClickListener { goCategoryFragment(6) }
        binding.ivMarried.setOnClickListener { goCategoryFragment(7) }
        binding.ivHealth.setOnClickListener { goCategoryFragment(8) }
        binding.ivWedding.setOnClickListener { goCategoryFragment(9) }
        binding.ivFuture.setOnClickListener { goCategoryFragment(10) }
        binding.ivPresent.setOnClickListener { goCategoryFragment(11) }
        binding.ivPast.setOnClickListener { goCategoryFragment(12) }

        binding.clQuestion.setOnClickListener {
            val action = CreateQnaFragmentDirections.actionGlobalWriteAnswerFragment(id)
            findNavController().navigate(action)
        }
    }

    private fun goCategoryFragment(id: Long) {
        val action = CreateQnaFragmentDirections.actionCreateQnaFragmentToCategoryFragment(id)
        findNavController().navigate(action)
    }
}
