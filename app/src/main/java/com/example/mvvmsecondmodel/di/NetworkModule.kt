package com.example.mvvmsecondmodel.di

import com.example.mvvmsecondmodel.data.remote.ApiClient
import org.koin.dsl.module

val networkModule = module {
    single { ApiClient.provideOkHttpClient(get()) }
    single { ApiClient.provideApiClient(get()) }
}