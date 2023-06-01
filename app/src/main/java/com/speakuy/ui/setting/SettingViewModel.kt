package com.speakuy.ui.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.speakuy.ui.auth.SettingPreferences
import kotlinx.coroutines.launch

class SettingViewModel(private val pref: SettingPreferences) : ViewModel() {

    fun deleteTokenPref() {
        viewModelScope.launch {
            pref.deleteToken()
        }
    }
}