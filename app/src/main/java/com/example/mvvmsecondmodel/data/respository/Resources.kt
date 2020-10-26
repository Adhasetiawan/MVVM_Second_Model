package com.example.mvvmsecondmodel.data.respository

import com.example.mvvmsecondmodel.util.Status


sealed class Resource<out T>(val status: Status, val data: T?, val message: String?) {

    data class Loading<T>(val loadingData: T?) : Resource<T>(Status.LOADING, loadingData, null)
    data class Success<T>(val successData: T?) : Resource<T>(Status.SUCCESS, successData, null)
    data class Error<T>(val msg: String, val error: T?) : Resource<T>(Status.ERROR, error, msg)

//    companion object {
//
//        fun <T> Success(data: T?): Resource<T> {
//            return Resource(Status.SUCCESS, data,null)
//        }
//
//        fun <T> Error(msg: String, data: T? = null): Resource<T> {
//            return Resource(Status.ERROR, data, msg)
//        }
//
//        fun <T> Loading(data: T? = null): Resource<T> {
//            return Resource(Status.LOADING, data, null)
//
//        }
//    }
}