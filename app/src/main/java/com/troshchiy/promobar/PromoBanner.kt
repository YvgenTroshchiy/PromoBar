package com.troshchiy.promobar

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

class PromoBanner @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        inflate(context, R.layout.promo_banner, this)

//    private fun updateBanner() {
//        val bounds = Rect()
//        val descriptionPaint = binding.description.paint
//
//        descriptionPaint.getTextBounds(details, 0, details.length, bounds)
//
//        val fontMetrics = descriptionPaint.fontMetrics
//
//        val height: Int = bounds.height()
//        val width: Int = bounds.width()
//
//        val promoBannerWidth = binding.promoBanner.measuredWidthAndState
//
//        val measuredWidth = binding.promoBanner.measuredWidth
//
//        var detailsResult1 = ""
//        val split = details.split(" ")
//
//        var index = 0
//        for (i in 0..split.size) {
//            detailsResult1 += split[i]
//
//            val measureText = descriptionPaint.measureText(detailsResult1)
//            if (measureText > promoBannerWidth) {
//                break
//            }
//            index = i
//        }
//
//        binding.description.text = detailsResult1
//    }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)

        setMeasuredDimension(width, height)
    }

    fun update(details: String, code: String, button: String) {
    }
}