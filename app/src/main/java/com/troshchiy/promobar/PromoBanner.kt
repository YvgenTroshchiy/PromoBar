package com.troshchiy.promobar

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.TextAppearanceSpan
import android.util.AttributeSet
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

    private var previousTouchTime = 0L
    private var previousTouchedX = 0F
    private var previousTouchedY = 0F

    init {

        // If we set onClick to the message view it consumes click and motionLayout will not work.
        binding.motionLayout.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    previousTouchTime = System.currentTimeMillis()
                    previousTouchedX = event.x
                    previousTouchedY = event.y
                }
                MotionEvent.ACTION_UP -> {
                    if (previousTouchedX == event.x && previousTouchedY == event.y) {
                        if (System.currentTimeMillis() - previousTouchTime >= ViewConfiguration.getLongPressTimeout()) {
                            onLongClick(context)
                        } else {
                            onClick(context)
                        }
                    }
                }
            }

            return@setOnTouchListener false
        }

        binding.close.setExpandedTouchArea(12F)
        binding.close.setOnClickListener { visibility = GONE }
    }

    private fun onLongClick(context: Context) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        clipboard.setPrimaryClip(ClipData.newPlainText(code, code))

        Toast.makeText(context, context.getString(R.string.coupon_code_copied), Toast.LENGTH_SHORT).show()
    }

    private fun onClick(context: Context) {
        Toast.makeText(context, "OnClick", Toast.LENGTH_SHORT).show()
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