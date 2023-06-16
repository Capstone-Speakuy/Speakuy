package com.speakuy.ui.matching

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.speakuy.R
import com.speakuy.databinding.ActivityDetailBinding
import com.speakuy.model.Mentor

@Suppress("DEPRECATION")
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mentorData = intent.getParcelableExtra<Mentor>("mentorData") as Mentor
        with (binding) {
            Glide.with(imgAvatarDetail.context)
                .load(mentorData.photo)
                .apply(RequestOptions.placeholderOf(R.mipmap.ic_launcher).override(200,200))
                .into(imgAvatarDetail)
            tvNameDetail.text = mentorData.name
            tvDescDetail.text = mentorData.description
        }
    }
}