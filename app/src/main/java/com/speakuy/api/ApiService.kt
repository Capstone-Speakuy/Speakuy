package com.speakuy.api

import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @POST("mentee/register")
    fun registerx(
        @Body user: User
    ): Call<ApiResponse>

    @POST("mentee/loginAuth")
    fun loginx(
        @Body user: User
    ): Call<LoginResponse>

    @FormUrlEncoded
    @POST("register")
    fun register(
        @Field("name") name : String,
        @Field("email") email : String,
        @Field("password") password : String
    ): Call<ApiResponse>

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email : String,
        @Field("password") password : String
    ): Call<LoginResponse>


    @GET("mentors")
    fun getMentors(): Call<MentorResponse>

    @GET("mentors/my-mentors")
    fun getMyMentor(): Call<MentorResponse>

//    @Multipart
//    @POST("stories")
//    fun upload(
//        @Part file: MultipartBody.Part,
//        @Part("description") description: RequestBody
//    ): Call<ApiResponse>
}