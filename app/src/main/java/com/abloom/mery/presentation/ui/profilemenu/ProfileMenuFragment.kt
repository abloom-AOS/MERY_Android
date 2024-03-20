package com.abloom.mery.presentation.ui.profilemenu

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.abloom.mery.R
import com.abloom.mery.databinding.FragmentProfileMenuBinding
import com.abloom.mery.presentation.common.base.BaseFragment
import com.abloom.mery.presentation.common.view.setOnNavigationClick
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileMenuFragment :
    BaseFragment<FragmentProfileMenuBinding>(R.layout.fragment_profile_menu) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.appbarProfileMenu.setOnNavigationClick {
            findNavController().popBackStack()
        }
        binding.button6.setOnClickListener {
            findNavController().navigate(ProfileMenuFragmentDirections.actionProfileMenuFragmentToConnectFragment())
        }
    }
}
