package com.example.api_movie_app.data.repositories

import com.example.api_movie_app.data.local_db.MovieDao
import com.example.api_movie_app.data.models.Movie
import com.example.api_movie_app.data.remote_db.MovieRemoteDataSource
import com.example.api_movie_app.utils.performFetching
import com.example.api_movie_app.utils.performFetchingAndSaving
import kotlinx.coroutines.flow.Flow
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

    fun getMovieById(id : Int) = performFetchingAndSaving(
        { localDataSource.getMovieById(id) },
        { remoteDataSource.getMovieId(id) },
        { localDataSource.updateMovies(it.results) } // Add instead of Update???
    )

    fun getMovieByName(name : String) = performFetchingAndSaving(
        { localDataSource.getMoviesByName(name) },
        { remoteDataSource.getMoviesName(name) },
        { localDataSource.addMovies(it.results) } // Add 1 movie instead of many???
    )

    fun getCocktailsByName(name: String, flag: Boolean) = performFetching {
        localDataSource.getMoviesByName(name)
    }

    fun addMovie(movie: Movie) {
        localDataSource.addMovie(movie)
    }

//    fun deleteMovie() {
//        localDataSource.deleteMovie()
//    }

    fun deleteMovies() {
        localDataSource.deleteMovies()
    }

    fun updateMovie(movie: Movie) {
        localDataSource.updateMovie(movie)
    }


    fun getFavoriteMovies() = performFetching {
        localDataSource.getFavoritesMovies()
    }

}
