package com.example.health_food.retrofit

import com.example.health_food.model.CommunityDTO
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
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

    @FormUrlEncoded
    @POST("/getNickname")
    fun postGetNickName(
        @Field("userId") id:String
    ) : Call<String>

    @FormUrlEncoded
    @POST("/emailAuth")
    fun postEmailAuth(
        @Field("userId") id: String
    ): Call<String>

    @FormUrlEncoded
    @POST("/pwChange")
    fun postPwChange(
        @Field("userId") id: String,
        @Field("password") pw: String
    ): Call<String>

    @FormUrlEncoded
    @POST("/isTasteDiv")
    fun postIsTasteDiv(
        @Field("userId") id:String,
        @Field("local") local: String,
        @Field("health") health: String
    ) : Call<String>

    @FormUrlEncoded
    @POST("/nameChange")
    fun postNameChange(
        @Field("userId") id: String,
        @Field("nickname") username: String
    ) : Call<String>

    @FormUrlEncoded
    @POST("/upload")
    fun postUpload(
        @Field("userId") id: String,
        @Field("picture") picture: String,
        @Field("tag") tag: String,
        @Field("content") content: String
    ) : Call<String>

    @GET("/list/1")
    fun getList(): Call<CommunityDTO>
}