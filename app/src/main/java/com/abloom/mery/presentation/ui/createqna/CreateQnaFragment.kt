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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.appbarCreateQna.setOnNavigationClick {
            findNavController().popBackStack()
        }
        binding.button2.setOnClickListener {
            findNavController().navigate(CreateQnaFragmentDirections.actionCreateQnaFragmentToCategoryFragment())
        }
        binding.button3.setOnClickListener {
            findNavController().navigate(
                CreateQnaFragmentDirections.actionGlobalWriteAnswerFragment(1)
            )
        }
    }
}
