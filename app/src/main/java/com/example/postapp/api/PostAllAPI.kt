package com.example.postapp.api

import com.example.postapp.models.ErrorResponse
import com.example.postapp.models.createCommentModel.CreateCommentReq
import com.example.postapp.models.createCommentModel.CreateCommentRes
import com.example.postapp.models.postCommentModel.PostCommentModel
import com.example.postapp.models.postDetailsModel.PostDetailsModel
import com.example.postapp.models.postModel.PostModel
import com.haroldadmin.cnradapter.NetworkResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

// All api interfaces
interface PostAllAPI {
    @GET(".")
    suspend fun getPost() : NetworkResponse<PostModel, ErrorResponse>

    @GET("{id}")
    suspend fun getPostDetails(@Path("id") id: Int) : NetworkResponse<PostDetailsModel, ErrorResponse>

    @GET("{id}/comments")
    suspend fun getPostComment(@Path("id") id: Int) : NetworkResponse<PostCommentModel, ErrorResponse>

    @POST("{id}/comments")
    suspend fun createPostComment(@Body requestBody: CreateCommentReq) : NetworkResponse<CreateCommentRes, ErrorResponse>
}