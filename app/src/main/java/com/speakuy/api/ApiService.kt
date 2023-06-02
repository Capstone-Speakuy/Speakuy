package com.speakuy.api

import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @FormUrlEncoded
    @POST("mentee/register")
    fun registerx(
        @Field("full_name") full_name : String,
        @Field("email") email : String,
        @Field("password") password : String
    ): Call<ApiResponse>

    @FormUrlEncoded
    @POST("mentee/loginAuth")
    fun loginx(
        @Field("email") email : String,
        @Field("password") password : String
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

//    @Multipart
//    @POST("stories")
//    fun upload(
//        @Part file: MultipartBody.Part,
//        @Part("description") description: RequestBody
//    ): Call<ApiResponse>
//
//    @GET("stories")
//    fun storiesLocation(
//        @Query("location") location: Int
//    ): Call<StoryResponse>

//    @GET("stories")
//    suspend fun getStoryPaged(
//        @Query("page") page: Int,
//        @Query("size") size: Int
//    ): Mentor
}