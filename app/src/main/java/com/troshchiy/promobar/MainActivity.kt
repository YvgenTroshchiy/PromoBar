package com.troshchiy.promobar

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ImageSpan
import android.text.style.ReplacementSpan
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.chip.ChipDrawable
import com.troshchiy.promobar.databinding.ActivityMainBinding
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bounds = Rect()

        val details = "Get 30% off your first order with code"
        val code = "JVN"
        val button = "SEE DETAILS"

        val message = "$details $code $button"

        binding.description.paint.getTextBounds(details, 0, details.length, bounds)

        val height: Int = bounds.height()
        val width: Int = bounds.width()

        val promoBannerWidth = binding.promoBanner.width

        val chipDrawable = ChipDrawable.createFromResource(this, R.xml.standalone_chip)
        chipDrawable.setBounds(0, 0, chipDrawable.intrinsicWidth, chipDrawable.intrinsicHeight)
        chipDrawable.text = code

        val ssb = SpannableStringBuilder(message)
        val span = ImageSpan(chipDrawable)
//        ssb.setSpan(span, details.length, details.length + code.length + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        val start = details.length + 1
        val end = start + code.length
        ssb.setSpan(RoundedBackgroundSpan(this), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        binding.oneLineBanner.text = ssb
    }
}

class RoundedBackgroundSpan(
    context: Context,
    @ColorInt private val textColor: Int = context.resources.getColor(R.color.black, null),
    @ColorInt private val backgroundColor: Int = context.resources.getColor(R.color.purple_200, null)
) : ReplacementSpan() {

    private val additionalVerticalPadding = 4.toPx().toFloat()
    private val additionalHorizontalPadding = 8.toPx().toFloat()
    private val cornerRadius = 4.toPx().toFloat()

    override fun draw(canvas: Canvas, text: CharSequence, start: Int, end: Int, x: Float, top: Int, y: Int, bottom: Int, paint: Paint) {
        val newTop = y - additionalVerticalPadding + paint.fontMetrics.ascent
        val newBottom = y + paint.fontMetrics.descent + additionalVerticalPadding
        val right = x + paint.measureText(text, start, end) + 2 * additionalHorizontalPadding
        val rect = RectF(x, newTop, right, newBottom)
        paint.color = backgroundColor

        canvas.drawRoundRect(rect, cornerRadius, cornerRadius, paint)
        paint.color = textColor
        canvas.drawText(text, start, end, x + additionalHorizontalPadding, y.toFloat(), paint)
    }

    override fun getSize(paint: Paint, text: CharSequence?, start: Int, end: Int, fm: Paint.FontMetricsInt?): Int {
        return (paint.measureText(text, start, end) + 2 * additionalHorizontalPadding).toInt()
    }
}

fun Int.toPx(): Int {
    val metrics = Resources.getSystem().displayMetrics
    return (this * (metrics.densityDpi / 160.0f)).roundToInt()
}