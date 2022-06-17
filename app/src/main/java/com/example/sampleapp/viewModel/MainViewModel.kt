package com.example.sampleapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidnetworking.error.ANError
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
    val breeds = MutableLiveData<List<String>>()
    val amountOfResult = MutableLiveData(0)
    val checkboxState = MutableLiveData(false)

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

                override fun error(error: ANError) {
                    println("Error happened ${error.errorBody}")
                }

                override fun breedListSuccess(data: HashMap<String, List<String>>?) {}
            })
        }
    }


    fun getBreeds() {
        viewModelScope.launch {
            repository.getBreeds(object: DataCallback {
                override fun success(data: List<String>?) {}

                override fun error(error: ANError) {
                    println("Get breed error ${error.errorBody}")
                }

                override fun breedListSuccess(data: HashMap<String, List<String>>?) {
                    println("Get breed success")
                    data?.let {
                        breeds.postValue(it.keys.toList())
                    }
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