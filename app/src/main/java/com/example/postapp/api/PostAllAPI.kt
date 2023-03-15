package com.example.postapp.api

import com.example.postapp.models.ErrorResponse
import com.example.postapp.models.postDetailsModel.PostDetailsModel
import com.example.postapp.models.postModel.PostModel
import com.haroldadmin.cnradapter.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

// All api interfaces
interface PostAllAPI {
    @GET(".")
    suspend fun getPost() : NetworkResponse<PostModel, ErrorResponse>

    @GET("{id}")
    suspend fun getPostDetails(@Path("id") id: Int) : NetworkResponse<PostDetailsModel, ErrorResponse>
}