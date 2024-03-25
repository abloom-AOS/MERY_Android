package com.abloom.mery.presentation.common.util

import android.content.res.Resources
import android.widget.Toast
import com.google.android.material.tabs.TabLayout
import kotlin.math.roundToInt

val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density).roundToInt()


fun TabLayout.onTabSelected(onTabSelected:(tab: TabLayout.Tab) -> Unit){
    this.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
        override fun onTabSelected(tab: TabLayout.Tab) {
            onTabSelected.invoke(tab)
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {}

        override fun onTabReselected(tab: TabLayout.Tab?) {}
    })
}
