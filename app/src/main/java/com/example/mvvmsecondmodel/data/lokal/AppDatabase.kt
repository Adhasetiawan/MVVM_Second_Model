package com.example.mvvmsecondmodel.data.lokal

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvmsecondmodel.data.lokal.dao.MovieDao
import com.example.mvvmsecondmodel.data.lokal.entity.Movie

@Database(
    entities =[Movie::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun MovieDao(): MovieDao

    companion object {
        fun getInstance(context: Context) : AppDatabase = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java, "movie_db"
        ).build()
    }
}