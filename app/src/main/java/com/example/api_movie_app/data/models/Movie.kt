package com.example.api_movie_app.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class Movie(
    var isFavoriteMovie: Int = 0,
    val year: Int,
    val title: String,
    val review: String,
    val image: String?,
    @PrimaryKey(autoGenerate = true) val id: Int
) {
    constructor(isFavoriteMovie: Int, title: String, year: Int, review: String, image: String?
        ) : this(
        isFavoriteMovie,
        year,
        title,
        review,
        image,
        0
    )
}