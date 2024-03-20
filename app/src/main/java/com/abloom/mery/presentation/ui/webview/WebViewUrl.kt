package com.abloom.mery.presentation.ui.webview

import androidx.annotation.StringRes
import com.abloom.mery.R

enum class WebViewUrl(val url: String, @StringRes val titleId: Int) {
    TERMS_OF_USE("/webTermsOfUse", R.string.webview_terms_of_use),
    PRIVACY_POLICY("/webPrivacyPolicy", R.string.webview_privacy_policy),
    SENSITIVE_PRIVACY("/webSensitivePrivacy", R.string.webview_sensitive_privacy),
    QUESTION_FACTORY("/questionFactory", R.string.webview_question_factory),
    CS_CENTER("/csCenter", R.string.webview_cs_center);
}
