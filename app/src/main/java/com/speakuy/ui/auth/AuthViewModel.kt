package com.speakuy.ui.auth

import android.util.Log
import androidx.lifecycle.*
import com.speakuy.api.ApiConfig
import com.speakuy.api.ApiResponse
import com.speakuy.api.LoginResponse
import com.speakuy.api.User
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthViewModel(private val pref: SettingPreferences) : ViewModel() {
    private val _response = MutableLiveData<ApiResponse>()
    val response: LiveData<ApiResponse> = _response
    private val _loginResponse = MutableLiveData<LoginResponse>()
    val loginResponse: LiveData<LoginResponse> = _loginResponse

    fun login(userEmail: String, userPass: String) {
        val client = ApiConfig.getApiService().login(userEmail, userPass)
        client.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    _loginResponse.value = response.body()
                }
            }
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e("tes", "onFailure: ", t)
            }
        })
    }

    fun register(userName: String, userEmail: String, userPass: String) {
        val client = ApiConfig.getApiService().register(userName, userEmail, userPass)
        client.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    _response.value = response.body()
                }
            }
            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Log.e("tes", "onFailure: ", t)
            }
        })
    }

    fun loginx(user: User) {
        val client = ApiConfig.getApiService().loginx(user)
        client.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    _loginResponse.value = response.body()
                }
            }
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e("tes", "onFailure: ", t)
            }
        })
    }

    fun registerx(user: User) {
        val client = ApiConfig.getApiService().registerx(user)
        client.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    _response.value = response.body()
                }
            }
            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Log.e("tes", "onFailure: ", t)
            }
        })
    }

    fun getTokenPref(): LiveData<String?> = pref.getToken().asLiveData()

    fun saveTokenPref(token: String) {
        viewModelScope.launch {
            pref.saveToken(token)
        }
    }
}