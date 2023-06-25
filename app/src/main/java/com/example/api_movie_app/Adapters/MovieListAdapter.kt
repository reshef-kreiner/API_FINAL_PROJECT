package com.example.api_movie_app.Adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.api_movie_app.data.models.Movie
import com.example.api_movie_app.databinding.ItemMovieBinding


class MovieListAdapter(private val listener: MovieItemListener) :
    RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>() {

    private val movies = ArrayList<Movie>()

    class MovieViewHolder(private val itemBinding: ItemMovieBinding,
                          private val listener: MovieItemListener)
        : RecyclerView.ViewHolder(itemBinding.root),
        View.OnClickListener {

        private lateinit var movie: Movie

        init {
            itemBinding.root.setOnClickListener(this)
            itemBinding.favoriteIcon.setOnClickListener(this)
        }

        fun bind(item: Movie) {
            this.movie = item
            itemBinding.movieTitle.text = item.title
            Glide.with(itemBinding.root)
                .load("https://www.themoviedb.org/t/p/original" + item.image)
                .into(itemBinding.movieImage)

            itemBinding.favoriteIcon.isSelected = item.isFavoriteMovie == 1

            itemBinding.favoriteIcon.setOnClickListener() {
                Log.i("FavoriteIcon","Favorite icon click")
                itemBinding.favoriteIcon.isSelected = item.isFavoriteMovie == 0
                onFavoriteClick(item)
            }

        }

        fun onFavoriteClick(movie: Movie) {
            listener.onFavoriteClick(movie)
        }

        override fun onClick(v: View?) {
            listener.onMovieClick(movie)
        }
    }

    fun setMovies(movies : Collection<Movie>) {
        this.movies.clear()
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MovieViewHolder(binding,listener)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) =
        holder.bind(movies[position])


    override fun getItemCount() = movies.size

    interface MovieItemListener {
        fun onMovieClick(movieId : Movie)
        fun onFavoriteClick(movie: Movie)
    }
}