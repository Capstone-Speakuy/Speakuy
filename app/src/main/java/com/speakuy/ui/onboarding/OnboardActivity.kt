package com.speakuy.ui.onboarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.speakuy.R

class OnboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboard)
        supportActionBar?.hide()
    }
}