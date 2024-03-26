package com.abloom.mery.presentation.ui.signup

import android.os.Bundle
import android.view.View
import com.abloom.mery.R
import com.abloom.mery.databinding.FragmentInputNameBinding
import com.abloom.mery.presentation.common.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InputNameFragment : BaseFragment<FragmentInputNameBinding>(R.layout.fragment_input_name) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
    }

    private fun initListener() {
        binding.editTextClearBut.setOnClickListener {
            clearInputName()
        }
    }

    private fun clearInputName() {
        binding.editText.editableText.clear()
    }


}