package id.wikosac.speakuy.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.wikosac.speakuy.R
import id.wikosac.speakuy.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

    }
}