package com.abloom.mery.presentation.common.util

import android.content.res.Resources
import kotlin.math.roundToInt

val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density).roundToInt()

