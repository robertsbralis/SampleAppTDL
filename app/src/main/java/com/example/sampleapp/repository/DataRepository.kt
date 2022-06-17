package com.example.sampleapp.repository

interface DataRepository {

    fun getData(breed: String, callback: DataCallback)

    fun getBreeds(callback: DataCallback)
}
