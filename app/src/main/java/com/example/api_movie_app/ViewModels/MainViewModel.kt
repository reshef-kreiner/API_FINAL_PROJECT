package com.example.api_movie_app.ViewModels



import androidx.lifecycle.ViewModel
import com.example.api_movie_app.data.repository.CocktailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor( cocktailRepository: CocktailRepository) : ViewModel(){
    val margaritas = cocktailRepository.getCocktailsByName("%margarita%")
    val mojitos = cocktailRepository.getCocktailsByName("%mojito%")
    val pina = cocktailRepository.getCocktailsByName("%pina%")
    val mCocktails = cocktailRepository.getCocktailsByName("%m%")
    val jCocktails = cocktailRepository.getCocktailsByName("%j%")
    val allCocktails = cocktailRepository.getCocktails()
    val randomCocktail = cocktailRepository.getRandomCocktails()
}