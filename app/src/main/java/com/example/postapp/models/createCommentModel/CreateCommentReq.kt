package com.example.postapp.models.createCommentModel

data class CreateCommentReq(
    val body: String,
    val email: String,
    val name: String
)