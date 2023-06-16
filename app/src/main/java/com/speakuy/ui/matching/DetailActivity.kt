package com.speakuy.ui.matching

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.speakuy.R
import com.speakuy.databinding.ActivityDetailBinding
import com.speakuy.model.Mentor
import com.speakuy.ui.chat.ChatActivity

@Suppress("DEPRECATION")
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with (supportActionBar!!) {
            title = "Detail"
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        }

        val mentorData = intent.getParcelableExtra<Mentor>("mentorData") as Mentor
        with (binding) {
            Glide.with(imgAvatarDetail.context)
                .load(mentorData.photo)
                .apply(RequestOptions.placeholderOf(R.mipmap.ic_launcher).override(200,200))
                .into(imgAvatarDetail)
            tvNameDetail.text = mentorData.name
            tvDescDetail.text = mentorData.description

        }

        binding.btnChatDetail.setOnClickListener {
            startActivity(Intent(this, ChatActivity::class.java))
        }
    }
}