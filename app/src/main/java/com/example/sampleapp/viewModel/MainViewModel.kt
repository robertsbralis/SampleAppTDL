package com.example.sampleapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleapp.repository.DataCallback
import com.example.sampleapp.repository.DataRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: DataRepositoryImpl) : ViewModel() {

    // Live data
    val images = MutableLiveData<List<String>>()

    // State flow
    private val _state = MutableStateFlow(DataState.Success(emptyList()))
    val state: StateFlow<DataState> = _state


    fun getImages(breed: String) {
        viewModelScope.launch {
            repository.getData(breed, object : DataCallback {

                override fun success(data: List<String>?) {
                    println("Success")
                    data?.let {
                        //images.postValue(it)
                        _state.value = DataState.Success(it)
                    }
                }

                override fun error() {
                    println("Error happened")
                }
            })
        }
    }
}

sealed class DataState {
    data class Success(var data: List<String>) : DataState()
    data class Error(var exception: Throwable) : DataState()
}