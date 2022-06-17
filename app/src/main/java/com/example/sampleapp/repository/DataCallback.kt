package com.example.sampleapp.repository

import com.androidnetworking.error.ANError

interface DataCallback {

    fun success(data: List<String>?)

    fun error(error: ANError)

    fun breedListSuccess(data: HashMap<String, List<String>>?)

}
