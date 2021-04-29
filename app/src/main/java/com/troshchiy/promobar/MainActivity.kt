package com.troshchiy.promobar

import android.graphics.Rect
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ImageSpan
import android.text.style.TextAppearanceSpan
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.chip.ChipDrawable
import com.troshchiy.promobar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val details = "Get 30% off your first order with code"
    private val code = "APPITUP"
    private val button = "SEE DETAILS"

    private val message = "$details $code $button"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        updateOneLineBanner()

        val bounds = Rect()
        binding.description.paint.getTextBounds(details, 0, details.length, bounds)

        val height: Int = bounds.height()
        val width: Int = bounds.width()

        val promoBannerWidth = binding.promoBanner.width

        val chipDrawable = ChipDrawable.createFromResource(this, R.xml.standalone_chip)
        chipDrawable.setBounds(0, 0, chipDrawable.intrinsicWidth, chipDrawable.intrinsicHeight)
        chipDrawable.text = code

        val span = ImageSpan(chipDrawable)
//        ssb.setSpan(span, details.length, details.length + code.length + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

    }

    private fun updateOneLineBanner() {
        val builder = SpannableStringBuilder(message)
        val promoCodeStart = details.length + 1
        val promoCodeEnd = promoCodeStart + code.length
        //        builder.setSpan(StyleSpan(Typeface.BOLD), promoCodeStart, promoCodeEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        builder.setSpan(TextAppearanceSpan(this, R.style.TextStyle), promoCodeStart, promoCodeEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        builder.setSpan(RoundedBackgroundSpan(this), promoCodeStart, promoCodeEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        builder.setSpan(TextAppearanceSpan(this, R.style.TextStyle), promoCodeEnd + 1, promoCodeEnd + 1 + button.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        binding.oneLineBanner.text = builder
    }
}

