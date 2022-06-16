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

    companion object {
        const val LIMIT = 10
    }

    // Live data
    val images = MutableLiveData<List<String>>()
    val amountOfResult = MutableLiveData(0)

    // State flow
    private val _state = MutableStateFlow(DataState.Success(emptyList()))
    val state: StateFlow<DataState> = _state


    fun getImages(breed: String) {
        viewModelScope.launch {
            repository.getData(breed, object : DataCallback {

                override fun success(data: List<String>?) {
                    println("Success")
                    data?.let {
                        images.postValue(it)
                        _state.value = DataState.Success(it)
                        amountOfResult.postValue(data.size)
                    }
                }

                override fun error() {
                    println("Error happened")
                }
            })
        }
    }

    fun getLimit(value: String) : Int {
        return if(value.isEmpty()) {
            LIMIT
        } else {
            value.toInt()
        }
    }
}

sealed class DataState {
    data class Success(var data: List<String>) : DataState()
    data class Error(var exception: Throwable) : DataState()
}