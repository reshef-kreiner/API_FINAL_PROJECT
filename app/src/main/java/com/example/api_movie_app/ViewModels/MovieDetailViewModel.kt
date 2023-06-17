package com.example.api_movie_app.ViewModels


import androidx.lifecycle.*
import com.example.api_movie_app.data.models.Movie
import com.example.api_movie_app.data.repositories.MovieRepository
import com.example.api_movie_app.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository): ViewModel() {

    private val mutableIsFavorite = MutableLiveData<Int>()

    // IN LECTURE
//    private val _id = MutableLiveData<Int>()
//    private val _movie = _id.switchMap { movieRepository.getMovie(it) } // WE DON'T HAVE getMovie

    val isFavorite: LiveData<Int> get() = mutableIsFavorite

    fun setIsFavorite(value: Int) {
        mutableIsFavorite.value = value
    }

    fun updateMovie(movie: Movie) = viewModelScope.launch(Dispatchers.IO) {
        movieRepository.updateMovie(movie)
    }

    private val mutableSelectedMovie = MutableLiveData<Movie>()
    val selectedMovie: LiveData<Movie> get() = mutableSelectedMovie

    fun selectMovie(movie: Movie) {
        mutableSelectedMovie.value = movie
    }
}