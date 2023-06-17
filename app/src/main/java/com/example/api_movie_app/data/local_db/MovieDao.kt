package com.example.api_movie_app.data.local_db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.api_movie_app.data.models.Movie

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    fun getAllMovies(): LiveData<List<Movie>>

    @Query("SELECT * FROM movies WHERE id = :movieId")
    fun getMovieById(movieId: Int): LiveData<List<Movie>>

    @Query("SELECT * FROM movies WHERE title LIKE :name")
    fun getMoviesByName(name : String) : LiveData<List<Movie>>

    @Query("SELECT * FROM movies WHERE isFavoriteMovie = 1")
    fun getFavoritesMovies() : LiveData<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMovie(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMovies(movie: List<Movie>)

    @Query("DELETE FROM movies WHERE id = :movieId")
    fun deleteMovie(movieId: Int)

    @Query("DELETE FROM movies")
    fun deleteMovies()

    @Update
    fun updateMovie(movie: Movie)

    @Update
    fun updateMovies(movies : List<Movie>)

}
