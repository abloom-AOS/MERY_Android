package com.abloom.mery.presentation.common.bindingadapter

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

@BindingAdapter("app:is_visible")
fun View.setVisible(isVisible: Boolean) {
    this.isVisible = isVisible
}
