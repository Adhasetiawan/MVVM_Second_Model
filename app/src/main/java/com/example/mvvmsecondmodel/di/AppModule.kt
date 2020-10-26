package com.example.mvvmsecondmodel.di

import com.example.mvvmsecondmodel.data.pref.SharedPreference
import com.example.mvvmsecondmodel.data.respository.MovieRespository
import com.example.mvvmsecondmodel.util.AppSchedulerProvider
import com.example.mvvmsecondmodel.util.NetworkHelper
import com.example.mvvmsecondmodel.util.SchedulerProvider
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import org.koin.dsl.module

val appModule = module {
    factory { GroupAdapter<ViewHolder>() }
    single<SchedulerProvider> { AppSchedulerProvider() }
    single { NetworkHelper(get()) }
    single { SharedPreference(get()) }
    single { MovieRespository(get(),get()) }
}