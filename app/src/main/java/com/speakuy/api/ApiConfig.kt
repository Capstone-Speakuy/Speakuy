package com.speakuy.api

import android.util.Log
import com.speakuy.BuildConfig
import com.speakuy.ui.auth.LoginFragment
import com.speakuy.ui.onboarding.OnboardActivity
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiConfig {
    companion object {
        fun getApiService(): ApiService {
            val loggingInterceptor = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            } else {
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
            }
            val authInterceptor = Interceptor { chain ->
                val req = chain.request()
                var token = ""
                if (LoginFragment.TOKEN_PREF.isNotEmpty()) {
                    token = LoginFragment.TOKEN_PREF
                    Log.d("testo", "login token: ${LoginFragment.TOKEN_PREF}")
                } else {
                    token = OnboardActivity.TOKEN_PREFERENCES
                    Log.d("testo", "onboard token: ${OnboardActivity.TOKEN_PREFERENCES}")
                }
                val reqHeaders = req.newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()
                chain.proceed(reqHeaders)
            }
            val client = OkHttpClient.Builder()
                .addInterceptor(authInterceptor)
                .addInterceptor(loggingInterceptor)
                .connectTimeout(30, TimeUnit.SECONDS) // Set the connection timeout
                .readTimeout(30, TimeUnit.SECONDS) // Set the read timeout
                .writeTimeout(30, TimeUnit.SECONDS) // Set the write timeout
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl("https://selesai-62grukbfuq-ue.a.run.app/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}
