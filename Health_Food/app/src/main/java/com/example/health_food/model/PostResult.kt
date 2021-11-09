package com.example.health_food.model

import com.google.gson.annotations.SerializedName

data class PostResult(
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("id")
    val id: Int,
    val title: String,
    @SerializedName("body")
    val bodyValue: String
)
