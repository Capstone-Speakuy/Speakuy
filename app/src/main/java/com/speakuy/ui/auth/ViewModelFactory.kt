package com.speakuy.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.speakuy.ui.matching.mentor.MentorViewModel
import com.speakuy.ui.onboarding.OnboardViewModel

class ViewModelFactory(private val pref: SettingPreferences) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            return AuthViewModel(pref) as T
        }
        if (modelClass.isAssignableFrom(MentorViewModel::class.java)) {
            return MentorViewModel(pref) as T
        }
        if (modelClass.isAssignableFrom(OnboardViewModel::class.java)) {
            return OnboardViewModel(pref) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}