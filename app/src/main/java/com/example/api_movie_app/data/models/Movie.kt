package com.example.api_movie_app.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class Movie(
    var isFavoriteMovie: Int = 0,
    @SerializedName("backdrop_path")
    val image: String,
    @PrimaryKey
    val id: Int,
    @SerializedName("original_language")
    val language : String,
    @SerializedName("overview")
    val summary: String,
    @SerializedName("release_date")
    val data: String,
    val title: String
    ) {
}