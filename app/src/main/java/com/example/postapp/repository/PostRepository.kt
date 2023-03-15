package com.example.postapp.repository

import com.example.postapp.api.PostAllAPI
import retrofit2.http.Query
import javax.inject.Inject

// Inject API Interface
class PostRepository @Inject constructor(private val postAllAPI: PostAllAPI) {

    suspend fun getPost() = postAllAPI.getPost()
    suspend fun getPostDetails(id: Int) = postAllAPI.getPostDetails(id)

}