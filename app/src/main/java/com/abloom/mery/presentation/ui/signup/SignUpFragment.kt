package com.abloom.mery.presentation.ui.signup

import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.abloom.mery.R
import com.abloom.mery.databinding.FragmentSignUpBinding
import com.abloom.mery.presentation.common.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : BaseFragment<FragmentSignUpBinding>(R.layout.fragment_sign_up) {

    private val childNavController: NavHostFragment by lazy {
        childFragmentManager.findFragmentById(
            R.id.fragmentContainerView
        ) as NavHostFragment
    }
    private val signUpNavController: NavController by lazy { childNavController.navController }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeProgressBar()
    }

    private fun observeProgressBar() {
        signUpNavController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.brideGroomSelectionFragment -> binding.signupProgressBar.progress = 1
                R.id.marryDateFragment -> binding.signupProgressBar.progress = 2
            }
        }
    }


}
