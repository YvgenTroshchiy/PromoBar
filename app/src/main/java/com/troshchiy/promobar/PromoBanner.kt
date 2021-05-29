package com.troshchiy.promobar

import android.annotation.SuppressLint
import android.content.Context
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.TextAppearanceSpan
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewConfiguration
import android.widget.FrameLayout
import android.widget.Toast
import com.troshchiy.promobar.databinding.PromoBannerBinding

@SuppressLint("ClickableViewAccessibility")
class PromoBanner @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val tag = "PromoBanner"

    private val details = "Get 30% off your first order with code"
    private val code = "APPITUP"
    private val button = "TERMS"

    private val message = "$details $code $button"

    private var binding: PromoBannerBinding = PromoBannerBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        var previousTouchTime = 0L
        var previousTouchedX = 0F
        var previousTouchedY = 0F

        // If we set onClick to the message view it consumes click and motionLayout will not work.
        binding.motionLayout.setOnTouchListener { v, event ->
            Log.i(tag, "event: $event")

            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    previousTouchTime = System.currentTimeMillis()
                    previousTouchedX = event.x
                    previousTouchedY = event.y
                }
                MotionEvent.ACTION_UP -> {
                    if (previousTouchedX == event.x && previousTouchedY == event.y) {
                        // Is Click action
                        if (System.currentTimeMillis() - previousTouchTime >= ViewConfiguration.getLongPressTimeout()) {
                            Toast.makeText(context, "OnClick", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "OnLongClick", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }

            return@setOnTouchListener false
        }

        binding.close.setExpandedTouchArea(12F)
        binding.close.setOnClickListener { visibility = GONE }
    }

    fun updateOneLineBanner() {
        val builder = SpannableStringBuilder(message)
        val promoCodeStart = details.length + 1
        val promoCodeEnd = promoCodeStart + code.length
        builder.setSpan(TextAppearanceSpan(context, R.style.TextStyle), promoCodeStart, promoCodeEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        builder.setSpan(RoundedBackgroundSpan(context), promoCodeStart, promoCodeEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        builder.setSpan(TextAppearanceSpan(context, R.style.TextStyle), promoCodeEnd + 1, promoCodeEnd + 1 + button.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        binding.message.text = builder
    }
}