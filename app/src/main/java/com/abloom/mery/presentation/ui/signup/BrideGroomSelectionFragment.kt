package com.abloom.mery.presentation.ui.signup

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.abloom.domain.user.model.Sex
import com.abloom.mery.R
import com.abloom.mery.databinding.FragmentBrideGroomSelectionBinding
import com.abloom.mery.presentation.common.base.BaseFragment

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BrideGroomSelectionFragment :
    BaseFragment<FragmentBrideGroomSelectionBinding>(R.layout.fragment_bride_groom_selection) {

    private val viewModel: SignUpViewModel by navGraphViewModels(R.id.signup_nav_graph)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
    }

    private fun initListener() {

        binding.groomBut.setOnClickListener {
            viewModel.selectSex(Sex.MALE)
            moveToMarryDateFragment()
            binding.groomBut.setBackgroundResource(R.drawable.signup_gender_selected)
        }

        binding.brideBut.setOnClickListener {
            viewModel.selectSex(Sex.FEMALE)
            moveToMarryDateFragment()
            binding.brideBut.setBackgroundResource(R.drawable.signup_gender_selected)
        }
    }

    private fun moveToMarryDateFragment() {
        findNavController().navigate(
            R.id.action_brideGroomSelectionFragment_to_marryDateFragment
        )
    }
}
