package com.example.api_movie_app.data.remote_db

import com.example.api_movie_app.data.models.Movie
import com.example.api_movie_app.data.models.AllMovies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("movie/popular")
    suspend fun getAllMovies() : Response<AllMovies>

    @GET("movies/{id}")
    suspend fun getMovieById(@Query("id") id: Int) : Response<AllMovies> // @Path?????

    @GET("movies/{name}")
    suspend fun getMoviesByName(@Query("s") name : String) : Response<AllMovies>
}