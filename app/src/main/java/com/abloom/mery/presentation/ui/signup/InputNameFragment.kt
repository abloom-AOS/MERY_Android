package com.abloom.mery.presentation.ui.signup

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import com.abloom.mery.R
import com.abloom.mery.databinding.FragmentInputNameBinding
import com.abloom.mery.presentation.common.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InputNameFragment : BaseFragment<FragmentInputNameBinding>(R.layout.fragment_input_name) {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
        initSaveEditTextName()
    }

    private fun initSaveEditTextName() {
        if (sharedViewModel.staticName != NULL_STRING)
            binding.inputNameEditText.setText(sharedViewModel.staticName)
    }

    private fun initListener() {
        binding.inputNameEditText.addTextChangedListener(afterTextChanged = { s ->
            sharedViewModel.setName(s.toString())
        })
    }

    companion object {
        private const val NULL_STRING = "null"
    }
}