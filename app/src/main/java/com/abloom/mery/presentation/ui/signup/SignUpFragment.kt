package com.abloom.mery.presentation.ui.signup

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.abloom.mery.R
import com.abloom.mery.databinding.FragmentSignUpBinding
import com.abloom.mery.presentation.common.base.BaseFragment
import com.abloom.mery.presentation.common.view.setOnActionClick
import com.abloom.mery.presentation.common.view.setOnNavigationClick
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : BaseFragment<FragmentSignUpBinding>(R.layout.fragment_sign_up) {

    private val signUpStepNavHostFragment: NavHostFragment by lazy {
        childFragmentManager.findFragmentById(
            R.id.fragmentContainerView
        ) as NavHostFragment
    }

    private val signUpStepNavController: NavController by lazy { signUpStepNavHostFragment.navController }
    private val signUpViewModel: SignUpViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
        initBindingViewModel()
        observeSignUpStepChanges()
    }

    private fun initBindingViewModel() {
        binding.viewModel = signUpViewModel
    }


    private fun initListener() {
        binding.appbarSignUp.setOnNavigationClick { handleNavigationBack() }

        binding.appbarSignUp.setOnActionClick {
            when (signUpStepNavController.currentDestination?.id) {
                R.id.marryDateFragment -> {
                    signUpStepNavController.navigate(
                        R.id.action_marryDateFragment_to_inputNameFragment
                    )
                }

                R.id.inputNameFragment -> {
                    //TODO("Step 04 약관 동의 프래그먼트로 이동하는 로직 구현")
                }
            }
        }
    }

    private fun handleNavigationBack() {
        when (signUpStepNavController.currentDestination?.id) {
            R.id.brideGroomSelectionFragment -> findNavController().popBackStack()
            else -> signUpStepNavController.popBackStack()
        }
    }

    private fun observeSignUpStepChanges() {
        signUpStepNavController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.brideGroomSelectionFragment -> {
                    setupForBrideGroomSelection()
                    updateProgressBarState(STEP_BRIDE_GROOM_SELECTION)
                }

                R.id.marryDateFragment -> {
                    setupForMarryDate()
                    updateProgressBarState(STEP_MARRY_DATE_SELECTION)
                }

                R.id.inputNameFragment -> {
                    setupForInputName()
                    updateProgressBarState(STEP_INPUT_NAME_SELECTION)
                }
            }
        }
    }

    private fun setupForBrideGroomSelection() {
        binding.appbarSignUp.apply {
            title = getString(R.string.signup_title)
            navigationText = getString(R.string.all_cancel)
            actionText = ""
            isActionEnabled = false
        }
    }

    private fun setupForMarryDate() {
        binding.appbarSignUp.apply {
            title = ""
            navigationIcon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_up_button)
            actionText = getString(R.string.all_next)
            isActionEnabled = true
        }
    }

    private fun setupForInputName() {
        binding.appbarSignUp.apply {
            title = ""
            navigationIcon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_up_button)
            actionText = getString(R.string.all_next)
            isActionEnabled = false
        }
    }

    private fun updateProgressBarState(state: Int) {
        binding.signupProgressBar.progress = state
    }

    companion object {
        private const val STEP_BRIDE_GROOM_SELECTION = 1
        private const val STEP_MARRY_DATE_SELECTION = 2
        private const val STEP_INPUT_NAME_SELECTION = 3
    }
}

