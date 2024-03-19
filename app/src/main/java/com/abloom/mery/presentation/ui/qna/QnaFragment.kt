package com.abloom.mery.presentation.ui.qna

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.abloom.mery.R
import com.abloom.mery.databinding.FragmentQnaBinding
import com.abloom.mery.presentation.common.base.BaseFragment
import com.abloom.mery.presentation.common.view.setOnNavigationClick
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QnaFragment : BaseFragment<FragmentQnaBinding>(R.layout.fragment_qna) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.appbarQna.setOnNavigationClick {
            findNavController().popBackStack()
        }
        binding.button8.setOnClickListener {
            findNavController().navigate(QnaFragmentDirections.actionQnaFragmentToConnectFragment())
        }
    }
}
