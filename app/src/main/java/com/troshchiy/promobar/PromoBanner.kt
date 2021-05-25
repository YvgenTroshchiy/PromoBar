package com.troshchiy.promobar

import android.content.Context
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.TextAppearanceSpan
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.troshchiy.promobar.databinding.PromoBannerBinding

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

    var binding: PromoBannerBinding = PromoBannerBinding.inflate(LayoutInflater.from(context), this, true)

    init {
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