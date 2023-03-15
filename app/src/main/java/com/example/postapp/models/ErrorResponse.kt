package com.example.postapp.models


import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("Error")
    var error: String? = "",
    @SerializedName("Response")
    var response: String? = ""
)