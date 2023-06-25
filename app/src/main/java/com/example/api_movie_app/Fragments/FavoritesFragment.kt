package com.example.api_movie_app.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.api_movie_app.Adapters.FavoritesAdapter
import com.example.api_movie_app.R
import com.example.api_movie_app.ViewModels.FavoritesViewModel
import com.example.api_movie_app.ViewModels.MovieDetailViewModel
import com.example.api_movie_app.data.models.Movie
import com.example.api_movie_app.databinding.FragmentFavoritesBinding
import com.example.api_movie_app.utils.Loading
import com.example.api_movie_app.utils.Success
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment(), FavoritesAdapter.MovieItemListener  {

    private val viewModel : FavoritesViewModel by viewModels()

    private val movieDetailViewModel: MovieDetailViewModel by activityViewModels()

    private var _binding: FragmentFavoritesBinding? = null

    private val binding get() = _binding!!

    private  lateinit var  adapter: FavoritesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoritesBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = FavoritesAdapter(this)
        binding.favoritesRV.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.favoritesRV.adapter = adapter

        viewModel.favoriteMovies.observe(viewLifecycleOwner) {
            Log.i("movies changed", "start")
            when (it.status) {
                is Loading -> {
                    Log.i("movies changed", "Loading")
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Success -> {
                    Log.i("movies changed", "Success")
                    binding.progressBar.visibility = View.GONE
                    adapter.setMovies(it.status.data!!)
                }

                is Error -> {
                    Log.i("movies changed", "Error")
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), it.status.message, Toast.LENGTH_LONG).show()
                }

            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.nav_menu,menu)
    }

    override fun onMovieClick(movie: Movie) {
        findNavController().navigate(R.id.action_favoritesFragment_to_movieDetailFragment)
        movieDetailViewModel.selectMovie(movie)
    }

    override fun onFavoriteClick(movie: Movie) {
        if (movie.isFavoriteMovie == 1) {
            movie.isFavoriteMovie = 0
        }
        adapter.removeMovie(movie)
        viewModel.updateMovie(movie)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}