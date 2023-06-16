package com.speakuy.ui.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.speakuy.ui.auth.SettingPreferences

class OnboardViewModel(private val pref: SettingPreferences) : ViewModel() {
    fun getTokenPref(): LiveData<String?> = pref.getToken().asLiveData()
}