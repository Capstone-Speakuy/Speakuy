package com.speakuy.api

data class ApiResponse(
//    val code: Int,
//    val status: String,
    val error: String,
    val message: String
)

data class LoginResponse(
    val error: String,
    val message: String,
    val loginResult: LoginResult?
)

data class LoginResult(
    val userId: String,
    val name: String,
    val token: String,
)

data class User(
    val full_name: String,
    val email: String,
    val phone_number: String? = null,
    val profile_picture: String? = null,
)

data class Mentor(
    val name: String,
    val photo: String,
    val location: String,
    val language: String
)