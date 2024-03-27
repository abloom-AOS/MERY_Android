package com.abloom.mery.presentation.ui.signup

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import com.abloom.mery.R
import com.abloom.mery.databinding.FragmentMarryDateBinding
import com.abloom.mery.presentation.common.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import sh.tyy.wheelpicker.DatePickerView

@AndroidEntryPoint
class MarryDateFragment : BaseFragment<FragmentMarryDateBinding>(R.layout.fragment_marry_date) {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.datePicker.setWheelListener(object : DatePickerView.Listener {
            override fun didSelectData(year: Int, month: Int, day: Int) {
                Log.e("TAG", "$year $month $day")
            }
        })
    }
}
