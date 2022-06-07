package com.example.sampleapp.networking

import com.androidnetworking.interfaces.ParsedRequestListener
import com.example.sampleapp.data.ApiResponse

interface Api {
    fun getImages(breed: String, callback: ParsedRequestListener<ApiResponse>)
}
