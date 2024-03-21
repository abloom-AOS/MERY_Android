package com.abloom.mery.presentation.ui.signup

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.abloom.mery.R
import com.abloom.mery.databinding.FragmentSignUpBinding
import com.abloom.mery.presentation.common.base.BaseFragment
import com.abloom.mery.presentation.common.view.setOnNavigationClick
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : BaseFragment<FragmentSignUpBinding>(R.layout.fragment_sign_up) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingInit()
    }

    private fun bindingInit() {

        binding.appbarSignUp.setOnNavigationClick {
            findNavController().popBackStack()
        }

        binding.groomBut.setOnClickListener {
            TODO("데이터 업데이트 후 Signup/Step02로 이동 하는 로직 구현")
        }

        binding.brideBut.setOnClickListener {
            TODO("데이터 업데이트 후 Signup/Step02로 이동 하는 로직 구현")
        }

    }

}
