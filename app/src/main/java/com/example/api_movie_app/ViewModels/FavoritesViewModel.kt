package com.example.api_movie_app.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api_movie_app.data.models.Cocktail
import com.example.api_movie_app.data.repository.CocktailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(val cocktailRepository: CocktailRepository) : ViewModel(){
    val favoriteCocktails = cocktailRepository.getFavoriteCocktails()

    fun updateCocktail(cocktail: Cocktail) = viewModelScope.launch(Dispatchers.IO) {
        cocktailRepository.updateCocktail(cocktail)
    }

}