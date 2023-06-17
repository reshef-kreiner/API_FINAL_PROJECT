package com.example.api_movie_app.Adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.OnReceiveContentListener
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.api_movie_app.R
import com.example.api_movie_app.data.models.Movie
import com.example.api_movie_app.databinding.FragmentMovieDetailBinding


class MovieListAdapter(private val listener: MovieItemListener) :
    RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>() {

    private val movies = ArrayList<Movie>()

    class MovieViewHolder(private val itemBinding: FragmentMovieDetailBinding,
                          private val listener: MovieItemListener)
        : RecyclerView.ViewHolder(itemBinding.root),
        View.OnClickListener {

        private lateinit var movie: Movie

        init {
            itemBinding.root.setOnClickListener(this)
            itemBinding.favorite.setOnClickListener(this)
        }

        fun bind(item: Movie) {
            this.movie = item
            itemBinding.name.text = item.title
            Glide.with(itemBinding.root)
                .load(item.image)
                //.circleCrop() USES IN LECTURE
                .into(itemBinding.image)

            if (item.isFavoriteMovie == 1) {
                itemBinding.favorite.isSelected = true
            }
            else {
                itemBinding.favorite.isSelected = false
            }

            // DO WE NEED BOTH ^^^ ????
            itemBinding.favorite.setOnClickListener() {
                Log.i("ffff","fffffff")
                if (item.isFavoriteMovie == 0) {
                    itemBinding.favorite.isSelected = true
                }
                else {
                    itemBinding.favorite.isSelected = false
                }
                onFavoriteClick(item)
            }

        }

        fun onFavoriteClick(movie: Movie) {
            listener.onFavoriteClick(movie)
        }

        override fun onClick(v: View?) {
            listener.onMovieClick(movie) //In Lecture Movie.id
        }
    }

    fun setMovies(cocktails : Collection<Movie>) {
        this.movies.clear()
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = FragmentMovieDetailBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MovieViewHolder(binding,listener)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) =
        holder.bind(movies[position])


    override fun getItemCount() = movies.size

    interface MovieItemListener { // CAN ADD MORE EVENTS IF WE WANT
        fun onMovieClick(movieId : Movie) // Int instead of Movie in LECTURE
        fun onFavoriteClick(movie: Movie)
    }
}