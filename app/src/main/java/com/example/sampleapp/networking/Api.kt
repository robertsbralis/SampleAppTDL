package com.example.sampleapp.networking

import com.androidnetworking.interfaces.ParsedRequestListener
import com.example.sampleapp.data.ApiResponse
import com.example.sampleapp.data.BreedListApiResponse

interface Api {
    fun getImages(breed: String, callback: ParsedRequestListener<ApiResponse>)

    fun getBreeds(callback: ParsedRequestListener<BreedListApiResponse>)
}
