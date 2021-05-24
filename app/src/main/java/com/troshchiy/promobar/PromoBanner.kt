package com.troshchiy.promobar

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.troshchiy.promobar.databinding.PromoBannerBinding

class PromoBanner @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val tag = "PromoBanner"

    var binding: PromoBannerBinding = PromoBannerBinding.inflate(LayoutInflater.from(context), this, true)

    init {

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

    var w = 0

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)


//        Log.w(tag, "onMeasure")
//
//        val width = MeasureSpec.getSize(widthMeasureSpec)
//        val height = MeasureSpec.getSize(heightMeasureSpec)
//
//        w = width
//
//        width
//        setMeasuredDimension(width, height)
    }

    fun update(details: String, code: String, button: String) {
        Log.w(tag, "update")

        val descriptionPaint = binding.description.paint
        val fontMetrics = descriptionPaint.fontMetrics

//        binding.description.text = details

        var detailsResult1 = ""
        val split = details.split(" ")

        var index = 0
        for (i in 0..split.size) {
            detailsResult1 += split[i]

            val measureText = descriptionPaint.measureText(detailsResult1)
            if (measureText > w) {
                break
            }
            index = i
        }

        binding.description.text = detailsResult1

        binding.code.text = code
        binding.seeDetails.text = button
    }
}