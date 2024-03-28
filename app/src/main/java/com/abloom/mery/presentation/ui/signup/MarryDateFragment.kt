package com.abloom.mery.presentation.ui.signup

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.abloom.mery.R
import com.abloom.mery.databinding.FragmentMarryDateBinding
import com.abloom.mery.presentation.common.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import sh.tyy.wheelpicker.DatePickerView
import java.time.LocalDate

@AndroidEntryPoint
class MarryDateFragment : BaseFragment<FragmentMarryDateBinding>(R.layout.fragment_marry_date) {

    private val signUpViewModel: SignUpViewModel by viewModels({ requireParentFragment() })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
        initSavedDate()
    }

    private fun initListener() {
        binding.datePicker.setWheelListener(object : DatePickerView.Listener {
            override fun didSelectData(year: Int, month: Int, day: Int) {
                if (checkDateRange(month + 1, day)) {
                    val marriageDate = LocalDate.of(year, month + 1, day)
                    signUpViewModel.selectMarriageDate(marriageDate)
                }
            }
        })
    }

    private fun checkDateRange(month: Int, day: Int) =
        ((month in MONTH_RANGE_MIN..MONTH_RANGE_MAX) && (day in DAY_RANGE_MIN..DAY_RANGE_MAX))

    private fun initSavedDate() {
        val dayValue = signUpViewModel.selectedMarriage.value
        binding.datePicker.setDate(dayValue.year, dayValue.monthValue - 1, dayValue.dayOfMonth)
    }

    companion object {
        private const val MONTH_RANGE_MIN = 1
        private const val MONTH_RANGE_MAX = 12
        private const val DAY_RANGE_MIN = 1
        private const val DAY_RANGE_MAX = 31
    }

}
