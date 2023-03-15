package com.example.postapp.di

import com.example.postapp.api.PostAllAPI
import com.example.postapp.api.RetrofitUtils.retrofitInstance
import com.example.postapp.utils.Constants.BASE_URL
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    @Named("apiPost")
    fun provideBaseUrl() = BASE_URL


    @Provides
    @Singleton
    fun provideRetrofitInstance(@Named("apiPost") BASE_URL: String, gson: Gson, httpClient: OkHttpClient): PostAllAPI =
        retrofitInstance(baseUrl = BASE_URL, gson, httpClient)
            .create(PostAllAPI::class.java)

}