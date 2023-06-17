package com.example.api_movie_app.Fragments


import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.api_movie_app.Adapters.MainFragmentAdapter
import com.example.api_movie_app.R
import com.example.api_movie_app.ViewModels.MainViewModel
import com.example.api_movie_app.ViewModels.MovieDetailViewModel
import com.example.api_movie_app.data.models.Cocktail
import com.example.api_movie_app.databinding.FragmentMainPageBinding
import com.example.api_movie_app.ui.description_page.DescriptionCocktailViewModel
import com.example.api_movie_app.utils.Loading
import com.example.api_movie_app.utils.Success
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels()

    private var _binding: FragmentMainPageBinding? = null

    private val binding get() = _binding!!

    private lateinit var adapter: MainFragmentAdapter

    private val movieDetailViewModel: MovieDetailViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMainPageBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MainFragmentAdapter(this)
        binding.cocktailsRv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.cocktailsRv.adapter = adapter

        viewModel.mojitos.observe(viewLifecycleOwner) {
        }
        viewModel.margaritas.observe(viewLifecycleOwner) {
        }
        viewModel.pina.observe(viewLifecycleOwner) {
        }
        viewModel.mCocktails.observe(viewLifecycleOwner) {
        }
        viewModel.jCocktails.observe(viewLifecycleOwner) {
        }

        viewModel.allCocktails.observe(viewLifecycleOwner) {
            Log.i("cocktails changed","start")
            when (it.status) {
                is Success -> {
                    Log.i("cocktails changed","Success")
                }
                is Error -> {
                    Log.i("cocktails changed","Error")
                    //binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), it.status.message, Toast.LENGTH_LONG).show()
                }
            }
        }
        viewModel.randomCocktail.observe(viewLifecycleOwner) {
            Log.i("cocktails changed", "start")
            when (it.status) {
                is Loading -> {
                    Log.i("cocktails changed", "Loading")
                    binding.cocktailsRv.visibility = View.INVISIBLE
                    binding.cocktailsOfTheDay.visibility = View.INVISIBLE
                    binding.progressBarMain.visibility = View.VISIBLE
                }
                is Success -> {
                    Log.i("cocktails changed", "Success")
                    binding.progressBarMain.visibility = View.GONE
                    binding.cocktailsRv.visibility = View.VISIBLE
                    binding.cocktailsOfTheDay.visibility = View.VISIBLE
                    adapter.setCocktails(it.status.data!!)
                }

                is Error -> {
                    Log.i("cocktails changed", "Error")
                    binding.progressBarMain.visibility = View.GONE
                    Toast.makeText(requireContext(), it.status.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    fun onCocktailClick(cocktail : Cocktail) {
        movieDetailViewModel.selectCocktail(cocktail)
        findNavController().navigate(R.id.action_mainPage_to_descriptionFragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}