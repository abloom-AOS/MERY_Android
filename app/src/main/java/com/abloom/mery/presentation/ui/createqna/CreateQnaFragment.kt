package com.abloom.mery.presentation.ui.createqna

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.abloom.mery.R
import com.abloom.mery.databinding.FragmentCreateQnaBinding
import com.abloom.mery.presentation.common.base.BaseFragment
import com.abloom.mery.presentation.common.view.setOnNavigationClick
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateQnaFragment : BaseFragment<FragmentCreateQnaBinding>(R.layout.fragment_create_qna) {

    //더미 데이터
    private val recommendQuestion by lazy { "동해물과 백둥산이...(추천 데이터)" }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.appbarCreateQna.setOnNavigationClick {
            findNavController().popBackStack()
        }
        initData()
        initListener()
    }

    private fun initData() {
        binding.tvTodayQuestion.text = recommendQuestion
    }

    private fun initListener() {
        binding.ivEconomy.setOnClickListener { goCategoryFragment("경제", "경제 데이터") }
        binding.ivCommunication.setOnClickListener { goCategoryFragment("소통", "소통 데이터") }
        binding.ivValues.setOnClickListener { goCategoryFragment("가치관", "가치관 데이터") }
        binding.ivLife.setOnClickListener { goCategoryFragment("생활", "생활 데이터") }
        binding.ivChildren.setOnClickListener { goCategoryFragment("자녀", "자녀 데이터") }
        binding.ivFamily.setOnClickListener { goCategoryFragment("가족", "가족 데이터") }
        binding.ivMarried.setOnClickListener { goCategoryFragment("부부관계", "부부관계 데이터") }
        binding.ivHealth.setOnClickListener { goCategoryFragment("건강", "건강 데이터") }
        binding.ivWedding.setOnClickListener { goCategoryFragment("결혼", "결혼 데이터") }
        binding.ivFuture.setOnClickListener { goCategoryFragment("미래", "미래 데이터") }
        binding.ivPresent.setOnClickListener { goCategoryFragment("현재", "현재 데이터") }
        binding.ivPast.setOnClickListener { goCategoryFragment("과거", "과거 데이터") }
    }

    private fun goCategoryFragment(key: String, data: String) {
        val action = CreateQnaFragmentDirections.actionCreateQnaFragmentToCategoryFragment(data)
        findNavController().navigate(action)
    }
}
