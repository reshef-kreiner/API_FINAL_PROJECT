package com.example.api_movie_app.utils

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.example.api_movie_app.data.models.AllMovies
import com.example.api_movie_app.data.models.Movie
import kotlinx.coroutines.Dispatchers
import retrofit2.Response

fun <T,A> performFetchingAndSaving(
    localDbFetch: () -> LiveData<T>,
    remoteDbFetch: suspend () ->Resource<A>,
    localDbSave: suspend (A) -> Unit) : LiveData<Resource<T>> =

    liveData(Dispatchers.IO) {

        emit(Resource.loading())

        val source = localDbFetch().map { Resource.success(it) }
        emitSource(source)

        val fetchResource = remoteDbFetch()

        if(fetchResource.status is Success)
            localDbSave(fetchResource.status.data!!)

        else if(fetchResource.status is Error){
            emit(Resource.error(fetchResource.status.message))
            emitSource(source)
        }
    }

fun <T> performFetching(localDbFetch: () -> LiveData<T> ) =

    liveData(Dispatchers.IO) {

        emit(Resource.loading())

        val source = localDbFetch().map { Resource.success(it) }
        emitSource(source)
    }
