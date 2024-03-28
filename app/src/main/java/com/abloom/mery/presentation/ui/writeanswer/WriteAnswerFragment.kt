package com.abloom.mery.presentation.ui.writeanswer

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.abloom.mery.R
import com.abloom.mery.databinding.FragmentWriteAnswerBinding
import com.abloom.mery.presentation.common.base.BaseFragment
import com.abloom.mery.presentation.common.view.setOnActionClick
import com.abloom.mery.presentation.common.view.setOnNavigationClick
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WriteAnswerFragment :
    BaseFragment<FragmentWriteAnswerBinding>(R.layout.fragment_write_answer) {

    private val viewModel: WriteAnswerViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.appbarWriteAnswer.setOnNavigationClick {
            findNavController().popBackStack()
        }
        binding.appbarWriteAnswer.setOnActionClick {
            findNavController().popBackStack(R.id.homeFragment, false)
        }
    }
}
