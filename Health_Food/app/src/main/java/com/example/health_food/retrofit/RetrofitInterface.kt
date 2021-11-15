package com.example.health_food.retrofit

import com.example.health_food.model.Login
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RetrofitInterface {
    @FormUrlEncoded
    @POST("/")
    fun getPostResult(
        @Field("userId") id: String,
        @Field("password") password: String
    ) : Call<Login>
}