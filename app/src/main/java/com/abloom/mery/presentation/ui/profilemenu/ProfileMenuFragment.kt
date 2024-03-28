package com.abloom.mery.presentation.ui.profilemenu

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.abloom.domain.user.model.MarriageState
import com.abloom.domain.user.model.Sex
import com.abloom.domain.user.model.User
import com.abloom.mery.R
import com.abloom.mery.databinding.FragmentProfileMenuBinding
import com.abloom.mery.presentation.common.base.BaseFragment
import com.abloom.mery.presentation.common.util.repeatOnStarted
import com.abloom.mery.presentation.common.view.setOnNavigationClick
import com.abloom.mery.presentation.ui.webview.WebViewUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileMenuFragment :
    BaseFragment<FragmentProfileMenuBinding>(R.layout.fragment_profile_menu) {

    private val viewModel: ProfileMenuViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAppBar()
        setupDataBinding()

        observeLoginUser()
        observeLoginUserDescriptionUi()
    }

    private fun setupAppBar() {
        binding.appbarProfileMenu.setOnNavigationClick { findNavController().popBackStack() }
    }

    private fun setupDataBinding() {
        binding.viewModel = viewModel
        binding.onConnectSettingButtonClick = ::navigateToConnect
        binding.onNavigateToWebViewButtonClick = ::navigateToWebView
    }

    private fun navigateToConnect() {
        findNavController().navigate(ProfileMenuFragmentDirections.actionProfileMenuFragmentToConnectFragment())
    }

    private fun navigateToWebView(url: WebViewUrl) {
        findNavController().navigate(
            ProfileMenuFragmentDirections.actionProfileMenuFragmentToWebViewFragment(url)
        )
    }

    private fun observeLoginUser() {
        repeatOnStarted { viewModel.loginUser.collect(::handleLoginUser) }
    }

    private fun handleLoginUser(user: User?) {
        binding.ivProfilemenuUserImage.setImageDrawable(
            when {
                user == null -> ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.img_profilemenu_not_login_user
                )

                user.sex == Sex.MALE -> ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.img_profilemenu_groom
                )

                user.sex == Sex.FEMALE -> ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.img_profilemenu_bride
                )

                else -> throw AssertionError("여기까지 올리가 없음.")
            }
        )

        binding.tvProfilemenuMarriageDate.text = if (user == null) {
            getString(R.string.profilemenu_request_login_for_marriage_date)
        } else {
            when (val marriageState = user.marriageState) {
                is MarriageState.BeforeMarriage -> getString(
                    R.string.profilemenu_until_marriage_date_format,
                    marriageState.daysUntilMarriage
                )

                MarriageState.WeddingDay -> getString(R.string.profilemenu_congratulate_for_wedding)
                is MarriageState.AfterMarriage -> getString(
                    R.string.profilemenu_since_marriage_date_format,
                    marriageState.daysSinceMarriage
                )
            }
        }
    }

    private fun observeLoginUserDescriptionUi() {
        repeatOnStarted { viewModel.loginUserDescriptionUiState.collect(::handleLoginUserDescriptionUi) }
    }

    private fun handleLoginUserDescriptionUi(description: LoginUserDescriptionUiState) {
        binding.tvProfilemenuLoginUserDescription.text = when (description) {
            LoginUserDescriptionUiState.NotLogin -> getString(R.string.profilemenu_press_and_login_button)
            LoginUserDescriptionUiState.NotConnected -> getString(R.string.profilemenu_press_and_connect)
            is LoginUserDescriptionUiState.Fiance -> {
                if (description.sex == Sex.MALE) {
                    getString(R.string.profilemenu_groom_name_format, description.name)
                } else {
                    getString(R.string.profilemenu_bride_name_format, description.name)
                }
            }
        }
        binding.tvProfilemenuLoginUserDescription.setOnClickListener {
            when (description) {
                LoginUserDescriptionUiState.NotLogin ->
                    findNavController().popBackStack(R.id.homeFragment, false)

                LoginUserDescriptionUiState.NotConnected ->
                    findNavController().navigate(ProfileMenuFragmentDirections.actionProfileMenuFragmentToConnectFragment())

                is LoginUserDescriptionUiState.Fiance -> {}
            }
        }
    }
}
