package com.troshchiy.promobar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.troshchiy.promobar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.promoBanner.updateOneLineBanner()
    }
}

