package com.example.api_movie_app.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api_movie_app.data.models.Movie
import com.example.api_movie_app.data.repositories.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(private val movieRepository: MovieRepository) : ViewModel(){
    val favoriteMovies = movieRepository.getFavoriteMovies()

    fun updateMovie(movie: Movie) = viewModelScope.launch(Dispatchers.IO) {
        movieRepository.updateMovie(movie)
    }

}