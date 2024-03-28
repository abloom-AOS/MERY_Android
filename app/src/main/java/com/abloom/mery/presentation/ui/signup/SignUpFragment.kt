package com.abloom.mery.presentation.ui.signup

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import com.abloom.mery.R
import com.abloom.mery.databinding.FragmentSignUpBinding
import com.abloom.mery.presentation.common.base.BaseFragment
import com.abloom.mery.presentation.common.view.setOnActionClick
import com.abloom.mery.presentation.common.view.setOnNavigationClick
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : BaseFragment<FragmentSignUpBinding>(R.layout.fragment_sign_up) {

    private val signUpViewModel: SignUpViewModel by viewModels()

    private val brideGroomSelectionFragment by lazy { BrideGroomSelectionFragment() }
    private val marryDateFragment by lazy { MarryDateFragment() }
    private val inputNameFragment by lazy { InputNameFragment() }

    private var curStep = STEP_BRIDE_GROOM_SELECTION

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
        initBindingViewModel()
        initTransaction()
        observeChildFragmentManager()
    }

    private fun initTransaction() {
        childFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, brideGroomSelectionFragment)
            .commit()
    }

    private fun observeChildFragmentManager() {
        childFragmentManager.registerFragmentLifecycleCallbacks(
            object : FragmentManager.FragmentLifecycleCallbacks() {
                override fun onFragmentCreated(
                    manager: FragmentManager,
                    curFragment: Fragment,
                    savedInstanceState: Bundle?
                ) {
                    when (curFragment.javaClass.simpleName) {
                        brideGroomSelectionFragment.javaClass.simpleName -> {
                            curStep = STEP_BRIDE_GROOM_SELECTION
                            setupForBrideGroomSelection()
                        }

                        marryDateFragment.javaClass.simpleName -> {
                            curStep = STEP_MARRY_DATE_SELECTION
                            setupForMarryDate()
                        }

                        inputNameFragment.javaClass.simpleName -> {
                            curStep = STEP_INPUT_NAME_SELECTION
                            setupForInputName()
                        }
                    }

                }
            }, true
        )

    }

    private fun initBindingViewModel() {
        binding.viewModel = signUpViewModel
    }

    private fun initListener() {
        binding.appbarSignUp.setOnNavigationClick { navigateToPriorFragment() }
        binding.appbarSignUp.setOnActionClick { navigateToNextFragment() }
    }

    private fun navigateToNextFragment() {
        when (curStep) {
            STEP_BRIDE_GROOM_SELECTION -> {
                childFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, marryDateFragment)
                    .commit()
            }

            STEP_MARRY_DATE_SELECTION -> {
                childFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, inputNameFragment)
                    .commit()
            }

            STEP_INPUT_NAME_SELECTION -> {
                //TODO("STEP 04 약간 동의 화면으로 이동)
            }
        }
    }

    private fun navigateToPriorFragment() {
        when (curStep) {
            STEP_BRIDE_GROOM_SELECTION -> {
                childFragmentManager.beginTransaction()
                    .remove(brideGroomSelectionFragment)
                    .commit()
            }

            STEP_MARRY_DATE_SELECTION -> {
                childFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, brideGroomSelectionFragment)
                    .commit()
            }

            STEP_INPUT_NAME_SELECTION -> {
                childFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, marryDateFragment)
                    .commit()
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
        updateProgressBarState(STEP_BRIDE_GROOM_SELECTION)
    }

    private fun setupForMarryDate() {
        binding.appbarSignUp.apply {
            title = ""
            navigationIcon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_up_button)
            actionText = getString(R.string.all_next)
            isActionEnabled = true
        }
        updateProgressBarState(STEP_MARRY_DATE_SELECTION)
    }

    private fun setupForInputName() {
        binding.appbarSignUp.apply {
            title = ""
            navigationIcon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_up_button)
            actionText = getString(R.string.all_next)
            isActionEnabled = false
        }
        updateProgressBarState(STEP_INPUT_NAME_SELECTION)
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

