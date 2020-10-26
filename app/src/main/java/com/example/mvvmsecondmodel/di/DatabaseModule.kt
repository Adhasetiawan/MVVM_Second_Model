package com.example.mvvmsecondmodel.di

import com.example.mvvmsecondmodel.data.lokal.AppDatabase
import com.example.mvvmsecondmodel.data.lokal.dao.MovieDao
import org.koin.dsl.module

val databaseModule = module {
    single { AppDatabase.getInstance(get()) }
    single { provideMovieDao(get()) }
}

fun provideMovieDao (database: AppDatabase):MovieDao = database.MovieDao()