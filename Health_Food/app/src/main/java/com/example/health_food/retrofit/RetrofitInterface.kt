package com.example.health_food.retrofit

import com.example.health_food.model.PostResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitInterface {
    @GET("posts/{post}")
    fun getPostResult(@Path("post") post: String) : Call<PostResult>
}