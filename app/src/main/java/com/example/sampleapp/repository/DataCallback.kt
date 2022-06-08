package com.example.sampleapp.repository

interface DataCallback {

    fun success(data: List<String>?)

    fun error()

}
