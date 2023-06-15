package com.speakuy.ui.matching

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.speakuy.databinding.ActivityMentorBinding

class MentorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMentorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMentorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.btnBack.setOnClickListener {
            startActivity(
                Intent(this, MatchingActivity::class.java)
            )
        }
    }
}