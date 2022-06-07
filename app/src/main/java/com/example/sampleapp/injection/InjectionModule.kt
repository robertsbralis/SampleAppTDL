package com.example.sampleapp.injection

import com.example.sampleapp.App
import com.example.sampleapp.networking.ApiManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class InjectionModule(private val context: App) {
    @Provides
    @Singleton
    fun provideApi() : ApiManager = ApiManager(context)
}
