package com.example.retrofit_practice

import android.util.Log
import retrofit2.Call
import retrofit2.http.*

interface NetworkService {
    @Headers("Authorization:Bearer 실제 토큰")
    @GET("users/repos")
    fun getRepos(): Call<List<create_repo>>
    @Headers("Authorization:Bearer 실제 토큰")
    @POST("user/repos")
    fun createRepo(@Body repo: create_repo): Call<create_repo>

}