package com.abloom.mery.presentation.ui.signup

import android.os.Bundle
import android.view.View
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.findNavController
import com.abloom.domain.user.model.Sex
import com.abloom.mery.R
import com.abloom.mery.databinding.FragmentBrideGroomSelectionBinding
import com.abloom.mery.presentation.common.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BrideGroomSelectionFragment :
    BaseFragment<FragmentBrideGroomSelectionBinding>(R.layout.fragment_bride_groom_selection) {

    private val signUpViewModel: SignUpViewModel by hiltNavGraphViewModels(R.id.signup_nav_graph)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
        initBrideAndGroomSelection()
    }

    private fun initBrideAndGroomSelection() {
        val selectedSex = signUpViewModel.selectedSex.value ?: return

        when (selectedSex) {
            Sex.MALE -> binding.groomBut.setBackgroundResource(R.drawable.signup_gender_selected)
            Sex.FEMALE -> binding.brideBut.setBackgroundResource(R.drawable.signup_gender_selected)
        }
    }

    private fun initListener() {
        binding.groomBut.setOnClickListener {
            signUpViewModel.selectSex(Sex.MALE)
            moveToMarryDateFragment()
        }

        binding.brideBut.setOnClickListener {
            signUpViewModel.selectSex(Sex.FEMALE)
            moveToMarryDateFragment()
        }
    }

    private fun moveToMarryDateFragment() {
        findNavController().navigate(
            R.id.action_brideGroomSelectionFragment_to_marryDateFragment
        )
    }
}
