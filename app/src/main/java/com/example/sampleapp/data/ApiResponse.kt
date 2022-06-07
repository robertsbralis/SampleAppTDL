package com.example.sampleapp.data

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("message") val message: List<String>
)
