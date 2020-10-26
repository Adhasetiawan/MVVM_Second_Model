package com.example.mvvmsecondmodel

import android.app.Application
import com.example.mvvmsecondmodel.di.appModule
import com.example.mvvmsecondmodel.di.databaseModule
import com.example.mvvmsecondmodel.di.networkModule
import com.example.mvvmsecondmodel.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin { androidContext(this@App)
        modules(listOf(networkModule, appModule, databaseModule, viewModelModule))}

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}