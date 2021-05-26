package com.troshchiy.promobar

import android.content.res.Resources
import android.graphics.Rect
import android.view.TouchDelegate
import android.view.View
import androidx.core.view.doOnLayout

val Float.dpToPx: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

fun View.setExpandedTouchArea(extraSpaceDp: Float = 12F) {
    val parent = parent as View
    val extraSpace = extraSpaceDp.dpToPx

    parent.doOnLayout {
        val touchableArea = Rect()

        getHitRect(touchableArea)

        touchableArea.top -= extraSpace
        touchableArea.bottom += extraSpace
        touchableArea.left -= extraSpace
        touchableArea.right += extraSpace

        parent.touchDelegate = TouchDelegate(touchableArea, this)
    }
}