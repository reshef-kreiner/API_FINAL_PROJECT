package com.example.api_movie_app.Fragments


import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.api_movie_app.Adapters.MovieListAdapter
import com.example.api_movie_app.R
import com.example.api_movie_app.ViewModels.MovieDetailViewModel
import com.example.api_movie_app.ViewModels.MovieListViewModel
import com.example.api_movie_app.data.loacal_db.CocktailDao
import com.example.api_movie_app.data.models.Cocktail
import com.example.api_movie_app.data.repository.CocktailRepository
import com.example.api_movie_app.databinding.FragmentCocktailsSearchBinding
import com.example.api_movie_app.ui.description_page.DescriptionCocktailViewModel
import com.example.api_movie_app.ui.favorites.FavoritesViewModel
import com.example.api_movie_app.utils.Loading
import com.example.api_movie_app.utils.Success
import dagger.hilt.android.AndroidEntryPoint
import com.example.api_movie_app.databinding.FragmentMovieListBinding


@AndroidEntryPoint
class MovieListFragment : Fragment(), MovieListAdapter.CocktailItemListe122ner {

    private val viewModel : MovieListViewModel by viewModels()

    private val movieDetailViewModel: MovieDetailViewModel by activityViewModels()

    private var _binding: FragmentMovieListBinding? = null

    private val binding get() = _binding!!

    private  lateinit var  adapter: MovieListAdapter

    private lateinit var searchView : SearchView

    private lateinit var myMenu: Menu

    private lateinit var menuInflater : MenuInflater

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = MovieListAdapter(this)
        binding.cocktailsRv.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.cocktailsRv.adapter = adapter

        viewModel.cocktails.observe(viewLifecycleOwner) {
            Log.i("cocktails changed","start")
            when (it.status) {
                is Loading -> {
                    Log.i("cocktails changed","Loading")
                    binding.progressBar.visibility = View.VISIBLE
                    binding.noResults.visibility = View.GONE
                }
                is Success -> {
                    Log.i("cocktails changed","Success")
                    binding.progressBar.visibility = View.GONE
                    binding.noResults.visibility = View.GONE
                    adapter.setCocktails(it.status.data!!)
                }

                is Error -> {
                    Log.i("cocktails changed","Error")
                    binding.progressBar.visibility = View.GONE
                    binding.noResults.visibility = View.GONE
                    Toast.makeText(requireContext(), it.status.message, Toast.LENGTH_LONG).show()
                }
            }
            if (adapter.itemCount == 0 && it.status !is Loading) {
                binding.noResults.visibility = View.VISIBLE
                //Toast.makeText(requireContext(), "No results", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onFavoriteClick(cocktail: Cocktail) {
        if (cocktail.isFavoriteCocktail == 0) {
            cocktail.isFavoriteCocktail = 1
        }
        else {
            cocktail.isFavoriteCocktail = 0
        }
        viewModel.updateCocktail(cocktail)
    }

    override fun onCocktailClick(cocktail: Cocktail) {
        //MenuItemCompat.collapseActionView(myMenu.findItem(R.id.cocktailsSearch))
        myMenu.clear()
        menuInflater.inflate(R.menu.nav_menu, myMenu)
        findNavController().navigate(R.id.action_cocktailsSearch_to_descriptionFragment)
        movieDetailViewModel.selectCocktail(cocktail)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater?.let { super.onCreateOptionsMenu(menu, it) }
        menu.clear()
        inflater?.inflate(R.menu.nav_menu, menu)
        menuInflater = inflater
        myMenu = menu
        val menuItem = menu.findItem(R.id.cocktailsSearch)
        searchView = menuItem.actionView as SearchView
        searchView.queryHint = "Search for a cocktail"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.setName(query)
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.setName(newText)
                return false
            }
        })

        menuItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                // TODO: do something...
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                //myMenu.clear()
                //menuInflater.inflate(R.menu.nav_menu, menu)
                activity?.onBackPressed()
                //findNavController().navigate(R.id.action_cocktailsSearch_to_mainPage)
                return true
            }
        })
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}