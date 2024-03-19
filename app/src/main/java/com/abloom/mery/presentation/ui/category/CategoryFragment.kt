package com.abloom.mery.presentation.ui.category

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.abloom.mery.R
import com.abloom.mery.databinding.FragmentCategoryBinding
import com.abloom.mery.presentation.common.base.BaseFragment
import com.abloom.mery.presentation.common.view.setOnNavigationClick
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : BaseFragment<FragmentCategoryBinding>(R.layout.fragment_category) {

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
    }
}
