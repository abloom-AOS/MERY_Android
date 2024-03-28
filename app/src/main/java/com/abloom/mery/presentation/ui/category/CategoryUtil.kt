package com.abloom.mery.presentation.ui.category

import com.google.android.material.tabs.TabLayout

fun TabLayout.onTabSelected(onTabSelected: (tab: TabLayout.Tab) -> Unit) {
    this.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab) {
            onTabSelected(tab)
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {}

        override fun onTabReselected(tab: TabLayout.Tab?) {}
    })
}
