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
        initListener()
    }

    private fun initListener() {

        binding.appbarSignUp.setOnNavigationClick {
            findNavController().popBackStack()
        }

        binding.groomBut.setOnClickListener {
            TODO("데이터 업데이트 후 Signup/Step02로 이동 하는 로직 구현")
        }

        binding.brideBut.setOnClickListener {
            TODO("데이터 업데이트 후 Signup/Step02로 이동 하는 로직 구현")
        }

        /*
            SignUp Step 04 구현 로직

              binding.button9.setOnClickListener {
                  findNavController().navigate(
                      SignUpFragmentDirections.actionSignUpFragmentToWebViewFragment(WebViewUrl.TERMS_OF_USE)
                  )
              }

              binding.button10.setOnClickListener {
                  findNavController().navigate(
                      SignUpFragmentDirections.actionSignUpFragmentToWebViewFragment(WebViewUrl.PRIVACY_POLICY)
                  )
              }
        */

    }

}
