package com.speakuy.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.speakuy.api.ApiConfig
import com.speakuy.api.ApiResponse
import com.speakuy.api.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthViewModel : ViewModel() {
    private val _response = MutableLiveData<ApiResponse>()
    val response: LiveData<ApiResponse> = _response
    private val _loginResponse = MutableLiveData<LoginResponse>()
    val loginResponse: LiveData<LoginResponse> = _loginResponse
    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    fun login(userEmail: String, userPass: String) {
        val client = ApiConfig.getApiService().login(userEmail, userPass)
        client.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    _loginResponse.value = response.body()
                }
                _message.value = _loginResponse.value?.message.toString()
            }
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                _message.value = t.message.toString()
            }
        })
    }

    fun register(userName: String, userEmail: String, userPass: String) {
        val client = ApiConfig.getApiService().register(userName, userEmail, userPass)
        client.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    _response.value = response.body()
                    _message.value = _response.value?.message.toString()
                } else {
                    _message.value = response.message()
                }
            }
            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                _message.value = t.message.toString()
            }
        })
    }
}