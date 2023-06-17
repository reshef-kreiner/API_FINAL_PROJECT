package com.example.api_movie_app.data.remote_db

import com.example.api_movie_app.data.models.Movie
import com.example.api_movie_app.utils.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRemoteDataSource @Inject constructor(
    private val movieService: MovieService) : BaseDataSource() {

    suspend fun getMovies() = getResult { movieService.getAllMovies() }

    suspend fun getMovieId(id : Int)= getResult { movieService.getMovieById(id) }

    suspend fun getMoviesName(name : String) = getResult { movieService.getMoviesByName(name) }


}