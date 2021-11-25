package com.example.health_food.retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    lateinit var api: RetrofitInterface
    init {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        var retrofit = Retrofit.Builder()
//            .baseUrl("http://10.120.74.70:3000")
            .baseUrl("http://10.120.74.70:3000")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        api = retrofit.create(RetrofitInterface::class.java)
    }
}