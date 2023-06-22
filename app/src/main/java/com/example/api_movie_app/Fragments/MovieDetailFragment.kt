package com.example.api_movie_app.Fragments


import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.view.MenuItemCompat
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.api_movie_app.R
import com.example.api_movie_app.ViewModels.MovieDetailViewModel
import com.example.api_movie_app.data.models.Movie
import com.example.api_movie_app.databinding.FragmentMovieDetailBinding
import com.example.api_movie_app.utils.Loading
import com.example.api_movie_app.utils.Success
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private val viewModel: MovieDetailViewModel by activityViewModels()

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.selectedMovie.observe(viewLifecycleOwner) {
            updateMovieViews(it)
            // DO WE WANT TO ADD THE WHEN(it.status)????? 1:42 in lecture loading success error
//            when (it.status) {
//                is Loading -> {
//                    Log.i("movies changed","Loading")
//                    binding.progressBar.visibility = View.VISIBLE
//                    binding.noResults.visibility = View.GONE
//                }
//                is Success -> {
//                    Log.i("movies changed","Success")
//                    binding.progressBar.visibility = View.GONE
//                    binding.noResults.visibility = View.GONE
//                    adapter.setMovies(it.status.data!!) // (ArrayList(it.status.data)) IN LECTURE
//                }
//
//                is Error -> {
//                    Log.i("movies changed","Error")
//                    binding.progressBar.visibility = View.GONE
//                    binding.noResults.visibility = View.GONE
//                    Toast.makeText(requireContext(), it.status.message, Toast.LENGTH_LONG).show()
//                }
//            }
        }
    }



    //   #########################################
    private fun updateMovieViews(movie: Movie) {
        binding.movieTitle.text = movie.title
        binding.language.text = movie.language
        binding.movieReview.text = movie.review
        binding.rating.text = movie.rating.toString()
        Glide.with(this).load(movie.image).into(binding.movieImage) // CircleCrop IN LECTURE
//        if (cocktail.strInstructions != null) binding.instructions.text =
//            cocktail.strInstructions else binding.bottomInstructionsLayout.visibility =
//            View.INVISIBLE
//
//
//        var ingredientsStr = cocktail.strIngredient1 + "\n" +
//                cocktail.strIngredient2 + "\n" +
//                cocktail.strIngredient3 + "\n" +
//                cocktail.strIngredient4 + "\n" +
//                cocktail.strIngredient5 + "\n" +
//                cocktail.strIngredient6 + "\n" +
//                cocktail.strIngredient7 + "\n" +
//                cocktail.strIngredient8 + "\n" +
//                cocktail.strIngredient9 + "\n" +
//                cocktail.strIngredient10 + "\n" +
//                cocktail.strIngredient11 + "\n" +
//                cocktail.strIngredient12 + "\n" +
//                cocktail.strIngredient13 + "\n" +
//                cocktail.strIngredient14 + "\n" +
//                cocktail.strIngredient15 + "\n"
//
//        var drinkMeasureStr = cocktail.strMeasure1 + "\n" +
//                cocktail.strMeasure2 + "\n" +
//                cocktail.strMeasure3 + "\n" +
//                cocktail.strMeasure4 + "\n" +
//                cocktail.strMeasure5 + "\n" +
//                cocktail.strMeasure6 + "\n" +
//                cocktail.strMeasure7 + "\n" +
//                cocktail.strMeasure8 + "\n" +
//                cocktail.strMeasure9 + "\n" +
//                cocktail.strMeasure10 + "\n" +
//                cocktail.strMeasure11 + "\n" +
//                cocktail.strMeasure12 + "\n" +
//                cocktail.strMeasure13 + "\n" +
//                cocktail.strMeasure14 + "\n" +
//                cocktail.strMeasure15 + "\n"
//
//
//        ingredientsStr = ingredientsStr.replace("null", "")
//        drinkMeasureStr = drinkMeasureStr.replace("null", "")
//        if(ingredientsStr.replace("\n", "").isNullOrBlank()) {
//            binding.ingredients.text = ""
//            binding.measures.text = ""
//            binding.noIngredients.visibility = View.VISIBLE
//        } else {
//            binding.ingredients.text = ingredientsStr
//            binding.measures.text = drinkMeasureStr
//        }
        binding.progressBar.visibility = View.GONE
        binding.detailLayout.visibility = View.VISIBLE
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.nav_menu,menu)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}