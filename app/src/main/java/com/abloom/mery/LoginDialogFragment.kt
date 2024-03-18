package com.abloom.mery


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.abloom.mery.databinding.FragmentLoginDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.AuthErrorCause
import com.kakao.sdk.user.UserApiClient

class LoginDialogFragment : BottomSheetDialogFragment()  {

    private lateinit var binding: FragmentLoginDialogBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentLoginDialogBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        kakaoAutoLogin()
        bindingSet()

    }

    private fun bindingSet() {

        binding.apply {

            kakaoLoginButton.setOnClickListener {
                checkUserApiClient()
            }

        }

    }

    private fun kakaoAutoLogin() {

        UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
            if (error != null)
                showToastMessage(KAKAO_LOGIN_FAILED)
            else if (tokenInfo != null)
                kakaoLoginSuccess()
        }

    }

    private fun kakaoLoginSuccess() {
        showToastMessage(KAKAO_LOGIN_SUCCESS)

        /*
        * 다음 화면으로 이동하는 로직 구현을 구현
        * 파이어베이스를 조회하여 기존 회원이 아닌 경우 회원가입 화면으로 이동한다.
        *
        * */

        dismiss()
    }

    private fun checkUserApiClient() {

        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->

            if (error != null) {
                when (error.toString()) {

                    AuthErrorCause.AccessDenied.toString() -> {
                        showToastMessage(ACCESS_DENIED)
                    }

                    AuthErrorCause.InvalidClient.toString() -> {
                        showToastMessage(INVALID_ERROR)
                    }

                    AuthErrorCause.InvalidGrant.toString() -> {
                        showToastMessage(CAN_NOT_AUTHENTICATION)
                    }

                    AuthErrorCause.InvalidRequest.toString() -> {
                        showToastMessage(REQUEST_PARAMETER_ERROR)
                    }

                    AuthErrorCause.InvalidScope.toString() -> {
                        showToastMessage(INVALID_SCOPE_ID)
                    }

                    AuthErrorCause.Misconfigured.toString() -> {
                        showToastMessage(SETTING_NOT_RIGHT)
                    }

                    AuthErrorCause.ServerError.toString() -> {
                        showToastMessage(SERVER_INTERNAL_ERROR)
                    }

                    AuthErrorCause.Unauthorized.toString() -> {
                        showToastMessage(NOT_HAVE_REQUEST_PERMISSION)
                    }

                    else -> {
                        showToastMessage(OTHER_ERROR)
                    }// Unknown

                }
            } else if (token != null) {
                kakaoLoginSuccess()
            }

        }

        if(UserApiClient.instance.isKakaoTalkLoginAvailable(requireContext()))
            UserApiClient.instance.loginWithKakaoTalk(requireContext(),callback = callback)
        else
            UserApiClient.instance.loginWithKakaoAccount(requireContext(),callback = callback)

    }

    private fun showToastMessage(message : String) {
        Toast.makeText(requireContext(), message , Toast.LENGTH_SHORT).show()
    }


    companion object{
        private const val KAKAO_LOGIN_FAILED = "카카오 로그인 실패"
        private const val KAKAO_LOGIN_SUCCESS = "카카오 로그인"
        private const val OTHER_ERROR = "기타 에러"
        private const val INVALID_ERROR = "유효하지 않은 앱"
        private const val ACCESS_DENIED = "접근이 거부 됨(동의 취소)"
        private const val REQUEST_PARAMETER_ERROR = "요청 파라미터 오류"
        private const val CAN_NOT_AUTHENTICATION = "인증 수단이 유효하지 않아 인증할 수 없는 상태"
        private const val INVALID_SCOPE_ID = "유효 하지 않은 scope ID"
        private const val SETTING_NOT_RIGHT = "설정이 올바르지 않음(android key hash)"
        private const val SERVER_INTERNAL_ERROR = "서버 내부 에러"
        private const val NOT_HAVE_REQUEST_PERMISSION = "앱이 요청 권한이 없음"
    }

}
