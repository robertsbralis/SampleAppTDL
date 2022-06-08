package com.example.sampleapp.injection

import com.example.sampleapp.App
import com.example.sampleapp.networking.ApiManager
import com.example.sampleapp.repository.DataRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class InjectionModule(private val context: App) {

    @Provides
    @Singleton
    fun provideApi() : ApiManager = ApiManager(context)

    @Provides
    @Singleton
    fun provideRepository() : DataRepositoryImpl = DataRepositoryImpl(provideApi())

}
