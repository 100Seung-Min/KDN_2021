package com.example.health_food.model

import com.google.gson.annotations.SerializedName

data class Login(
    @SerializedName("userId")
    val id: String,
    val password: String
)
