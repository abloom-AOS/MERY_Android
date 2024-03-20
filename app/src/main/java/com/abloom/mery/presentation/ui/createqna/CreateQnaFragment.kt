package com.abloom.mery.presentation.ui.createqna

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.abloom.mery.R
import com.abloom.mery.databinding.FragmentCreateQnaBinding
import com.abloom.mery.presentation.common.base.BaseFragment
import com.abloom.mery.presentation.common.view.setOnNavigationClick
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateQnaFragment : BaseFragment<FragmentCreateQnaBinding>(R.layout.fragment_create_qna) {

    private val recommendQuestion by lazy {"동해물과 백둥산이...(추천 데이터)"}
    private val data by lazy { "해당 질문 id값" }
    private val key by lazy { "질문 카테고리 종류 key값" }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.appbarCreateQna.setOnNavigationClick {
            findNavController().popBackStack()
        }
        initData()
        initListener()
    }

    private fun initData(){
        binding.tvTodayQuestion.text = recommendQuestion
    }

    private fun initListener(){
        binding.ivEconomy.setOnClickListener { goCategoryFragment(key, data) }
        binding.ivCommunication.setOnClickListener {goCategoryFragment(key,data)}
        binding.ivValues.setOnClickListener { goCategoryFragment(key,data) }
        binding.ivLife.setOnClickListener { goCategoryFragment(key,data) }
        binding.ivChildren.setOnClickListener { goCategoryFragment(key,data) }
        binding.ivFamily.setOnClickListener { goCategoryFragment(key,data) }
        binding.ivMarried.setOnClickListener { goCategoryFragment(key,data) }
        binding.ivHealth.setOnClickListener { goCategoryFragment(key,data) }
        binding.ivWedding.setOnClickListener { goCategoryFragment(key,data) }
        binding.ivFuture.setOnClickListener { goCategoryFragment(key,data) }
        binding.ivPresent.setOnClickListener { goCategoryFragment(key,data) }
        binding.ivPast.setOnClickListener { goCategoryFragment(key,data) }
    }


    private fun goCategoryFragment(key: String, data: String){
        val bundle = bundleOf(key to data)
        findNavController().navigate(R.id.action_createQnaFragment_to_categoryFragment,bundle)
    }

}
