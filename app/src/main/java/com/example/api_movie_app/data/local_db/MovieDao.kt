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

//    @Query("SELECT * FROM movies WHERE id = :movieId")
//    fun getMovieById(movieId: Int): LiveData<List<Movie>>

    @Query("SELECT * FROM movies WHERE isFavoriteMovie = 1")
    fun getFavoritesMovies() : LiveData<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMovies(movie: List<Movie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMovie(movie: Movie)

    @Update
    fun updateMovie(movie: Movie)


}
