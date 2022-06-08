package com.example.sampleapp.repository

import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.example.sampleapp.data.ApiResponse
import com.example.sampleapp.networking.ApiManager

class DataRepositoryImpl(private val apiManager: ApiManager) : DataRepository {

    override fun getData(breed: String, callback: DataCallback) {
        apiManager.getImages(breed, object: ParsedRequestListener<ApiResponse> {

            override fun onResponse(response: ApiResponse?) {
                callback.success(response?.message)
            }

            override fun onError(anError: ANError?) {
                callback.error()
            }
        })
    }
}
