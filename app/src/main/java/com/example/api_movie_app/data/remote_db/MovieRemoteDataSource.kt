package com.example.api_movie_app.data.remote_db

import android.util.Log
import com.example.api_movie_app.data.models.AllMovies
import com.example.api_movie_app.data.models.Movie
import com.example.api_movie_app.utils.Resource
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRemoteDataSource @Inject constructor(
    private val movieService: MovieService) : BaseDataSource() {

    suspend fun getMovies() = getResult {
        Log.d("API Call", "API call made") // Add this line to log the API call
        movieService.getAllMovies()
    }

//    suspend fun getMovieId(id : Int)= getResult { movieService.getMovieById(id) }

}