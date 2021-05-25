package com.troshchiy.promobar

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.TextAppearanceSpan
import androidx.appcompat.app.AppCompatActivity
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

//        updateOneLineBanner()

//        binding.promoBanner.update(details, code, button)
    }

//    private fun updateOneLineBanner() {
//        val builder = SpannableStringBuilder(message)
//        val promoCodeStart = details.length + 1
//        val promoCodeEnd = promoCodeStart + code.length
//        //        builder.setSpan(StyleSpan(Typeface.BOLD), promoCodeStart, promoCodeEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
//        builder.setSpan(TextAppearanceSpan(this, R.style.TextStyle), promoCodeStart, promoCodeEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
//        builder.setSpan(RoundedBackgroundSpan(this), promoCodeStart, promoCodeEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
//
//        builder.setSpan(TextAppearanceSpan(this, R.style.TextStyle), promoCodeEnd + 1, promoCodeEnd + 1 + button.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
//
//        binding.oneLineBanner.text = builder
//    }
}

