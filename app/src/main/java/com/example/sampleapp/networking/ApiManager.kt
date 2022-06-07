package com.example.sampleapp.networking

import android.content.Context
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.interfaces.ParsedRequestListener
import com.example.sampleapp.data.ApiResponse

class ApiManager(context: Context) : Api {

    init {
        AndroidNetworking.initialize(context)
    }

    companion object {
        private const val ENDPOINT = "https://dog.ceo/api"
    }

    override fun getImages(breed: String, callback: ParsedRequestListener<ApiResponse>) {
        AndroidNetworking.get("$ENDPOINT/breed/{breed}/images")
            .addPathParameter("breed", breed)
            .setPriority(Priority.LOW)
            .build()
            .getAsObject(ApiResponse::class.java, callback)
    }
}
