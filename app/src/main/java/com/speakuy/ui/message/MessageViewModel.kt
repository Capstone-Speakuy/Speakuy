package com.speakuy.ui.message

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.speakuy.api.ApiConfig
import com.speakuy.api.MentorResponse
import com.speakuy.ui.auth.SettingPreferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MessageViewModel(private val pref: SettingPreferences) : ViewModel() {

    private val _mentorResponse = MutableLiveData<MentorResponse>()
    val mentorResponse: LiveData<MentorResponse> = _mentorResponse
    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getMyMentor() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getMyMentor()
        client.enqueue(object : Callback<MentorResponse> {
            override fun onResponse(call: Call<MentorResponse>, response: Response<MentorResponse>) {
                if (response.isSuccessful) {
                    _mentorResponse.value = response.body()
                }
                Log.d("tes", "onResponse: ${response.body()}")
                _isLoading.value = false
            }
            override fun onFailure(call: Call<MentorResponse>, t: Throwable) {
                Log.e("tes", "mentorviewmodel onFailure: ", t)
                _message.value = t.message
                _isLoading.value = false
            }
        })
    }
}