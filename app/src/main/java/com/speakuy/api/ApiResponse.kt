package com.speakuy.api

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @field:SerializedName("code")
    val code: Int,
    @field:SerializedName("status")
    val status: String,
    @field:SerializedName("message")
    val message: String
)

data class LoginResponse(
    @field:SerializedName("code")
    val code: Int,
    @field:SerializedName("status")
    val status: String,
    @field:SerializedName("token")
    val token: String
)

//data class ApiResponse(
//    @field:SerializedName("error")
//    val error: Boolean,
//    @field:SerializedName("message")
//    val message: String
//)
//
//data class LoginResponse(
//    @field:SerializedName("error")
//    val error: Boolean,
//    @field:SerializedName("message")
//    val message: String,
//    @field:SerializedName("loginResult")
//    val loginResult: LoginResult
//)


data class LoginResult(
    val userId: String,
    val name: String,
    val token: String,
)

data class User(
    val full_name: String?,
    val email: String,
    val password: String
//    val phone_number: String? = null,
//    val profile_picture: String? = null,
)

data class Mentor(
    val name: String,
    val photo: String,
    val location: String,
    val language: String
)