package com.abloom.mery.presentation.common.bindingadapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@BindingAdapter("app:date_text")
fun TextView.setDateText(date: LocalDate) {
    text = date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG))
}
