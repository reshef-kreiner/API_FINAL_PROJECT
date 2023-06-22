package com.example.api_movie_app

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.MenuItemCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.onNavDestinationSelected
import com.example.api_movie_app.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController : NavController
    private lateinit var navHostFragment : NavHostFragment
    private lateinit var myMenu : Menu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding  = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
                as NavHostFragment
        navController = navHostFragment.navController

        // THIS IS WHAT HE USES IN LECTURE
        //val appBarConfiguration = AppBarConfiguration(navController.graph)
        //binding.toolbar.setupWithNavController(navController,appBarConfiguration)

        // THIS IS WHAT WE ARE USING
        supportActionBar?.title = ""
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#000000")))
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (menu != null) {
            myMenu = menu
        }
        menuInflater.inflate(R.menu.nav_menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val currentFragment = navController.currentDestination?.id // CHANGED FROM getId()
        if (currentFragment == R.id.movieListFragment && item.itemId != R.id.movieListFragment) {
            MenuItemCompat.collapseActionView(myMenu.findItem(R.id.movieListFragment))
        }
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }
}