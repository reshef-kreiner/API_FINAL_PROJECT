package com.example.api_movie_app.data.models

data class AllMovies (
    val page: Int,
    val results : List<Movie>,
    val totalPages : Int,
    val totalResults: Int
){

}