package com.example.postapp.models.createCommentModel

data class CreateCommentRes(
    val body: String,
    val email: String,
    val id: Int,
    val name: String,
    val post_id: Int
)