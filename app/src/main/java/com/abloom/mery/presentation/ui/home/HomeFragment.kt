package com.abloom.mery.presentation.ui.home

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.abloom.mery.R
import com.abloom.mery.databinding.FragmentHomeBinding
import com.abloom.mery.presentation.common.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToCreateQna())
        }
        binding.button5.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToProfileMenuFragment())
        }
        binding.button7.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToQnaFragment(1))
        }
        binding.button11.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSignUpFragment())
        }
    }
}
