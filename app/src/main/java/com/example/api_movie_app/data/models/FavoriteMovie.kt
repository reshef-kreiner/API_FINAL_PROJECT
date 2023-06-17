package com.example.api_movie_app.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteMovie(
    @PrimaryKey
    val id	: Int
)
