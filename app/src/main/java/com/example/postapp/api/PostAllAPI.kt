package com.example.postapp.api

import com.example.postapp.models.ErrorResponse
import com.example.postapp.models.PostModel
import com.haroldadmin.cnradapter.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Query

// All api interfaces
interface PostAllAPI {
    @GET(".")
    suspend fun getPost() : NetworkResponse<PostModel, ErrorResponse>
}