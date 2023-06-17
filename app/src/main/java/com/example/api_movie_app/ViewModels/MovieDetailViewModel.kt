package com.example.api_movie_app.ViewModels


import androidx.lifecycle.*
import com.example.api_movie_app.data.models.Cocktail
import com.example.api_movie_app.data.repository.CocktailRepository
import com.example.api_movie_app.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    val cocktailRepository: CocktailRepository): ViewModel() {

    private val mutableIsFavorite= MutableLiveData<Int>()
    val isFavorite: LiveData<Int> get() = mutableIsFavorite

    fun setIsFavorite(value: Int) {
        mutableIsFavorite.value = value
    }
    private val _id =  MutableLiveData<Int>()

    fun updateCocktail(cocktail: Cocktail) = viewModelScope.launch(Dispatchers.IO) {
        cocktailRepository.updateCocktail(cocktail)
    }


    private val mutableSelectedCocktail = MutableLiveData<Cocktail>()
    val selectedCocktail: LiveData<Cocktail> get() = mutableSelectedCocktail

    fun selectCocktail(cocktail: Cocktail) {
        mutableSelectedCocktail.value = cocktail
    }
}