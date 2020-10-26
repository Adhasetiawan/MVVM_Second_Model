package com.example.mvvmsecondmodel.data.remote

import com.example.mvvmsecondmodel.data.model.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("3/movie/popular")
    fun getMyMovie(@Query("api_key") api : String = "32bbbffe944d16d1d2a3ee46cfc6aaa0"
    ) : Single<MovieResponse.Movie>
}