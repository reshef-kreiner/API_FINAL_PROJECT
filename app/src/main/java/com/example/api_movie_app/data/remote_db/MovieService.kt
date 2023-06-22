package com.example.api_movie_app.data.remote_db

import com.example.api_movie_app.data.models.Movie
import com.example.api_movie_app.data.models.AllMovies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("movie/popular?api_key=a13e445388531e718cc651aefacac8cb")
    suspend fun getAllMovies() : Response<AllMovies>

//    @GET("movies/{id}?api_key=a13e445388531e718cc651aefacac8cb")
//    suspend fun getMovieById(@Path("id") id: Int) : Response<AllMovies> // @Query?????

}