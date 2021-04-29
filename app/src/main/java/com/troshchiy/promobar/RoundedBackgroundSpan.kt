package com.troshchiy.promobar

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.text.style.ReplacementSpan
import androidx.annotation.ColorInt
import kotlin.math.roundToInt

fun Int.toPx(): Int {
    val metrics = Resources.getSystem().displayMetrics
    return (this * (metrics.densityDpi / 160.0f)).roundToInt()
}

class RoundedBackgroundSpan(
    context: Context,
    @ColorInt private val textColor: Int = context.resources.getColor(R.color.black, null),
    @ColorInt private val backgroundColor: Int = context.resources.getColor(R.color.spot_coral_light, null)
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