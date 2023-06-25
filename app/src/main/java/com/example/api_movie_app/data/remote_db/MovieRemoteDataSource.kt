package com.example.api_movie_app.data.remote_db

import android.util.Log

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRemoteDataSource @Inject constructor(
    private val movieService: MovieService) : BaseDataSource() {

    suspend fun getMovies() = getResult {
        Log.d("API Call", "API call made")
        movieService.getAllMovies()
    }

}