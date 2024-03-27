package com.abloom.mery.presentation.ui.signup

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.abloom.mery.R
import com.abloom.mery.databinding.FragmentBrideGroomSelectionBinding
import com.abloom.mery.presentation.common.base.BaseFragment

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BrideGroomSelectionFragment :
    BaseFragment<FragmentBrideGroomSelectionBinding>(R.layout.fragment_bride_groom_selection) {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
        observeBrideAndGroomSelection()
    }

    private fun observeBrideAndGroomSelection() {
        sharedViewModel.getSex().observe(requireActivity()) { sex ->
            if (sex)
                binding.groomBut.setBackgroundResource(R.drawable.signup_gender_selected)
            else
                binding.brideBut.setBackgroundResource(R.drawable.signup_gender_selected)
        }
    }

    private fun initListener() {

        binding.groomBut.setOnClickListener {
            moveToMarryDateFragment()
            sharedViewModel.setSex(true)
        }

        binding.brideBut.setOnClickListener {
            moveToMarryDateFragment()
            sharedViewModel.setSex(false)
        }
    }

    private fun moveToMarryDateFragment() {
        findNavController().navigate(
            R.id.action_brideGroomSelectionFragment_to_marryDateFragment
        )
    }
}
