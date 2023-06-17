package com.example.api_movie_app.data.local_db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.api_movie_app.data.models.Movie
import com.example.api_movie_app.data.models.FavoriteMovie

@Dao
interface FavoriteMovieDao {

    @Query("SELECT * FROM favorites")
    fun  getAllFavoriteMovies() : LiveData<List<Int>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteMovie(favoriteMovie: FavoriteMovie)
}