package com.example.api_movie_app.ViewModels


import androidx.lifecycle.*
import com.example.api_movie_app.data.models.Movie
import com.example.api_movie_app.data.repositories.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel // DELETED FavoriteMovieRepository from constructor
class MovieListViewModel @Inject constructor(
    private val movieRepository: MovieRepository) : ViewModel() {

    val movies = movieRepository.getMovies()
    fun updateMovie(movie: Movie) = viewModelScope.launch(Dispatchers.IO) {
        movieRepository.updateMovie(movie)
    }

//    fun <T : Any?> MutableLiveData<T>.default(initialValue: T) = apply { setValue(initialValue) }
//
//    private var _name = MutableLiveData<String>().default("")
//
//    private var _id = MutableLiveData<Int>()
//
//
//
//    private val _movies = _name.switchMap {
//        //if(it == "") {
//        Log.i("start Search and Saving", "searching: %$it%")
//        movieRepository.getMovieByName("%$it%", true)
//        //movieRepository.getMovies()
//        //}
//        /*
//        else {
//            Log.i("letter Search", "%$it%")
//            movieRepository.getMoviesByName("%$it%",true)
//        }*/
//    }
//
//
//
//    val movies: LiveData<Resource<List<Movie>>> = _movies
//
//    fun setName(name: String) { // WE WANT TO CHANGE TO "setTitle"
//        _name.value = name
//    }

}
