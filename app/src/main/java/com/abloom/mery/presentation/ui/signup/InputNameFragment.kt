package com.abloom.mery.presentation.ui.signup

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
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
            binding.editText.setText(sharedViewModel.staticName)
    }

    private fun initListener() {
        binding.editTextClearBut.setOnClickListener {
            clearInputName()
        }

        binding.editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                sharedViewModel.setName(s.toString())
            }
        })
    }

    private fun clearInputName() {
        binding.editText.editableText.clear()
    }

    companion object {
        private const val NULL_STRING = "null"
    }
}