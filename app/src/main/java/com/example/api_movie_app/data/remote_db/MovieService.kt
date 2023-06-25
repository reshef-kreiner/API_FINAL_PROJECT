package com.example.api_movie_app.data.remote_db


import com.example.api_movie_app.data.models.AllMovies
import retrofit2.Response
import retrofit2.http.GET

interface MovieService {

    @GET("movie/popular?api_key=a13e445388531e718cc651aefacac8cb")
    suspend fun getAllMovies() : Response<AllMovies>

}