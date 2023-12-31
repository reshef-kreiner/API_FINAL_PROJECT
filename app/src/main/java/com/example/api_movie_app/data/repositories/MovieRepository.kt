package com.example.api_movie_app.data.repositories

import com.example.api_movie_app.data.local_db.MovieDao
import com.example.api_movie_app.data.models.Movie
import com.example.api_movie_app.data.remote_db.MovieRemoteDataSource
import com.example.api_movie_app.utils.performFetching
import com.example.api_movie_app.utils.performFetchingAndSaving
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val localDataSource: MovieDao,
    private val remoteDataSource : MovieRemoteDataSource) {

    fun getMovies() = performFetchingAndSaving(
        { localDataSource.getAllMovies() },
        { remoteDataSource.getMovies() },
        { localDataSource.addMovies(it.results) }
    )

    fun updateMovie(movie: Movie) {
        localDataSource.updateMovie(movie)
    }

    fun getFavoriteMovies() = performFetching {
        localDataSource.getFavoritesMovies()
    }

}
