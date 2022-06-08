package com.example.sampleapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sampleapp.repository.DataCallback
import com.example.sampleapp.repository.DataRepositoryImpl

class MainViewModel(private val repository: DataRepositoryImpl) : ViewModel() {

    val images = MutableLiveData<List<String>>()

    fun getImages(breed: String) {

        repository.getData(breed, object : DataCallback {

            override fun success(data: List<String>?) {
                println("Success")
                data?.let {
                    images.postValue(it)
                }
            }

            override fun error() {
                println("Error happened")
            }

        })
    }
}
