package com.troshchiy.promobar

import android.annotation.SuppressLint
import android.content.Context
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.TextAppearanceSpan
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.FrameLayout
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
//        binding.message.setOnClickListener {
//            Toast.makeText(context, "message", Toast.LENGTH_SHORT).show()
//        }
        binding.motionLayout.setOnTouchListener { v, event ->
            Log.i("PromoBanner", "event: $event")
            return@setOnTouchListener false
        }
        binding.close.setExpandedTouchArea(12F)
        binding.close.setOnClickListener { visibility = GONE }

         binding.motionLayout.setOnClickListener {
            Log.w("PromoBanner", "setOnClickListener")
         }
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