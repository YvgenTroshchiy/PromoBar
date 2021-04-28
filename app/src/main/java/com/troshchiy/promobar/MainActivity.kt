package com.troshchiy.promobar

import android.graphics.Rect
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.troshchiy.promobar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)

        val bounds = Rect()

        binding.description.paint.getTextBounds(
            "Get 30% off your first order with code",
            0, "Get 30% off your first order with code".length,
            bounds
        )

        val height: Int = bounds.height()
        val width: Int = bounds.width()

        val promoBannerWidth = binding.promoBanner.width

        val o = 0
    }
}