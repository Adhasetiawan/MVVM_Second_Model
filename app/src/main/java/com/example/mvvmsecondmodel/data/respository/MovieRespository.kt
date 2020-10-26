package com.example.mvvmsecondmodel.data.respository

import com.example.mvvmsecondmodel.data.lokal.dao.MovieDao
import com.example.mvvmsecondmodel.data.lokal.entity.Movie
import com.example.mvvmsecondmodel.data.model.MovieResponse
import com.example.mvvmsecondmodel.data.remote.ApiService
import com.example.mvvmsecondmodel.data.respository.metode3.PersistableNetworkResourceCall
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber

class MovieRespository (val apiService: ApiService, val movieDao: MovieDao) {

    fun getListMovie(): Observable<Resource<List<Movie>>>{
        return object : PersistableNetworkResourceCall<MovieResponse.Movie, List<Movie>>(){
            override fun loadFromDatabase(): Maybe<List<Movie>> {
                return movieDao.findAllMovie()
            }

            override fun createNetworkCall(): Single<MovieResponse.Movie> {
                return apiService.getMyMovie()
            }

            override fun onNetworkCallSuccess(
                emitter: ObservableEmitter<Resource<List<Movie>>>,
                response: MovieResponse.Movie
            ) {
                if (response.results.isEmpty()){
                    emitter.onNext(Resource.Error("data kosong", null))
                }else{
                    response.results.let {
                        Timber.d("input data" + it)
                        val semuadata = it.map { data -> Movie.from(data) }
                        movieDao.insert(semuadata)
                    }
                    emitter.setDisposable(movieDao.streamAll()
                        .subscribeBy(
                            onNext ={
                                emitter.onNext(Resource.Success(it))
                                Timber.d("cek data -> " + it)
                            }
                        ))
                }

            }
        }.resourceObservable
    }
}