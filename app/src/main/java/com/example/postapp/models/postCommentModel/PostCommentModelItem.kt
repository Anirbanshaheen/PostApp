package com.example.postapp.models.postCommentModel

import com.google.gson.annotations.SerializedName

data class PostCommentModelItem(
    @SerializedName("body")
    val body: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("post_id")
    val post_id: Int
)