package com.example.health_food.retrofit

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RetrofitInterface {
    @FormUrlEncoded
    @POST("/")
    fun postLogin(
        @Field("userId") id: String,
        @Field("password") password: String
    ) : Call<String>

    @FormUrlEncoded
    @POST("/signup")
    fun postSignUp(
        @Field("userId") id:String,
        @Field("password") password: String,
        @Field("email") email: String,
        @Field("nickname") nickname: String
    ):Call<String>

    @FormUrlEncoded
    @POST("/idOverlap")
    fun postIdOverlap(
        @Field("userId") id: String
    ): Call<String>

    @FormUrlEncoded
    @POST("/emailOverlap")
    fun postEmailOverlap(
        @Field("email") email: String
    ) : Call<String>
}