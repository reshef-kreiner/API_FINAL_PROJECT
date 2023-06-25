package com.example.api_movie_app.Fragments


import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.api_movie_app.Adapters.MovieListAdapter
import com.example.api_movie_app.R
import com.example.api_movie_app.ViewModels.MovieDetailViewModel
import com.example.api_movie_app.ViewModels.MovieListViewModel
import com.example.api_movie_app.data.models.Movie
import com.example.api_movie_app.utils.Loading
import com.example.api_movie_app.utils.Success
import dagger.hilt.android.AndroidEntryPoint
import com.example.api_movie_app.databinding.FragmentMovieListBinding


@AndroidEntryPoint
class MovieListFragment : Fragment(), MovieListAdapter.MovieItemListener {

    private val viewModel : MovieListViewModel by viewModels()

    private val movieDetailViewModel: MovieDetailViewModel by activityViewModels()

    private var _binding: FragmentMovieListBinding? = null

    private val binding get() = _binding!!

    private  lateinit var  adapter: MovieListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = MovieListAdapter(this)
        binding.moviesRv.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.moviesRv.adapter = adapter

        viewModel.movies.observe(viewLifecycleOwner) {
            Log.i("movies changed","start")
            when (it.status) {
                is Loading -> {
                    Log.i("movies changed","Loadingg")
                    binding.progressBar.visibility = View.VISIBLE
                    binding.noResults.visibility = View.GONE
                }
                is Success -> {
                    Log.i("movies changed","Success")
                    binding.progressBar.visibility = View.GONE
                    binding.noResults.visibility = View.GONE
                    adapter.setMovies(it.status.data!!)
                }

                is Error -> {
                    Log.i("movies changed","Error")
                    binding.progressBar.visibility = View.GONE
                    binding.noResults.visibility = View.GONE
                    Toast.makeText(requireContext(), it.status.message, Toast.LENGTH_LONG).show()
                }

            }

            if (adapter.itemCount == 0 && it.status !is Loading) {
                Log.i("movies changed","Item count is zeroo")
                binding.noResults.visibility = View.VISIBLE
            }
        }
    }

    override fun onFavoriteClick(movie: Movie) {
        if (movie.isFavoriteMovie == 0) {
            movie.isFavoriteMovie = 1
        }
        else {
            movie.isFavoriteMovie = 0
        }
        viewModel.updateMovie(movie)
    }

    override fun onMovieClick(movie: Movie) {
        findNavController().navigate(R.id.action_movieListFragment_to_movieDetailFragment)
        movieDetailViewModel.selectMovie(movie)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.nav_menu,menu)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}