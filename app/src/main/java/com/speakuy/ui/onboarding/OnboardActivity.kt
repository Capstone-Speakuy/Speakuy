package com.speakuy.ui.onboarding

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.speakuy.databinding.ActivityOnboardBinding
import com.speakuy.ui.auth.AuthViewModel
import com.speakuy.ui.auth.SettingPreferences
import com.speakuy.ui.auth.ViewModelFactory

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "login")

class OnboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardBinding
    private lateinit var onboardViewModel: OnboardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val pref = SettingPreferences.getInstance(dataStore)
        onboardViewModel = ViewModelProvider(this, ViewModelFactory(pref))[OnboardViewModel::class.java]
        onboardViewModel.getTokenPref().observe(this) {
            if (it != null) {
                TOKEN_PREFERENCES = it
            }
        }
    }

    companion object {
        var TOKEN_PREFERENCES = ""
    }
}