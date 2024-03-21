package com.abloom.mery.presentation.ui.category

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.abloom.mery.R
import com.abloom.mery.databinding.FragmentCategoryBinding
import com.abloom.mery.presentation.common.base.BaseFragment
import com.abloom.mery.presentation.common.view.setOnNavigationClick
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : BaseFragment<FragmentCategoryBinding>(R.layout.fragment_category) {

    // Navigation safe args 수신 args
    private val args: CategoryFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.appbarCategory.setOnNavigationClick {
            findNavController().popBackStack()
        }
        binding.button4.setOnClickListener {
            findNavController().navigate(
                CategoryFragmentDirections.actionGlobalWriteAnswerFragment(1)
            )
        }
        initData()
    }

    // Navigation safe args 수신확인 코드
    private fun initData() {
        Toast.makeText(requireActivity(), args.testData, Toast.LENGTH_SHORT).show()
    }
}
