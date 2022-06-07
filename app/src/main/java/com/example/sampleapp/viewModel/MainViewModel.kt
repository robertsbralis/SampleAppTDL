package com.example.sampleapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.example.sampleapp.data.ApiResponse
import com.example.sampleapp.networking.ApiManager

class MainViewModel(private val apiManager: ApiManager) : ViewModel() {

    val images = MutableLiveData<List<String>>()

    fun getImages(breed: String) {
        apiManager.getImages(breed, object: ParsedRequestListener<ApiResponse> {

            override fun onResponse(response: ApiResponse?) {
                println("Success $response")
                images.postValue(response?.message)
            }

            override fun onError(anError: ANError?) {
                println("Error happened $anError")
            }
        })
    }
}
