package com.example.postapp.models.postDetailsModel

import com.google.gson.annotations.SerializedName

data class PostDetailsModel(
    @SerializedName("body")
    val body: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("user_id")
    val user_id: Int
)