package com.example.mvvmsecondmodel.ui

import androidx.lifecycle.*
import com.example.mvvmsecondmodel.data.lokal.entity.Movie
import com.example.mvvmsecondmodel.data.respository.MovieRespository
import com.example.mvvmsecondmodel.data.respository.Resource
import com.example.mvvmsecondmodel.util.SchedulerProvider
import com.example.mvvmsecondmodel.util.Status
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.map

class MainViewModel(private val movieRespository: MovieRespository, val schedulerProvider: SchedulerProvider) : ViewModel() {

    private val disposables = CompositeDisposable()

    private val listmovie: MutableLiveData<Resource<List<Movie>>> = MutableLiveData()

    fun observerListMovie() : LiveData<Resource<List<Movie>>> = listmovie

    fun getListMovie(){
        movieRespository.getListMovie()
            .observeOn(schedulerProvider.ui())
            .subscribeOn(schedulerProvider.io())
            .subscribe({
                listmovie.postValue(Resource.Success(it.data))
            },{}).addTo(disposables)
    }
}