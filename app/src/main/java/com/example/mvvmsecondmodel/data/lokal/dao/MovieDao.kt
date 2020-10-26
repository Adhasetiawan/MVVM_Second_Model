package com.example.mvvmsecondmodel.data.lokal.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.mvvmsecondmodel.data.dao.BaseDao
import com.example.mvvmsecondmodel.data.lokal.entity.Movie
import io.reactivex.Flowable
import io.reactivex.Maybe
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao : BaseDao<Movie> {

    @Query("SELECT * FROM `movie` ORDER by movie_id DESC")
    fun findAllMovie() : Maybe<List<Movie>>

    @Query("SELECT * FROM `movie` ORDER by movie_id DESC")
    fun streamAll() : Flowable<List<Movie>>

    @Query("DELETE FROM `movie`")
    fun deleteAll()
}