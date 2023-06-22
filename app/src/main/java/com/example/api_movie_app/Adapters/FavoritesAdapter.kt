package com.example.api_movie_app.Adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.api_movie_app.data.models.Movie
import com.example.api_movie_app.databinding.FragmentMovieDetailBinding
import com.example.api_movie_app.databinding.ItemMovieBinding


class FavoritesAdapter(private val listener: MovieItemListener) :
    RecyclerView.Adapter<FavoritesAdapter.MovieViewHolder>() {

    private val favoriteMovies = ArrayList<Movie>()

    class MovieViewHolder(private val itemBinding: ItemMovieBinding,
                             private val listener: MovieItemListener
    )
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
                .load(item.image)
                .into(itemBinding.movieImage)
            itemBinding.favoriteIcon.isSelected = true


            itemBinding.favoriteIcon.setOnClickListener() {
                Log.i("ffff","fffffff")
                onFavoriteClick(item)
            }

        }

        override fun onClick(v: View?) {
            listener.onMovieClick(movie)
        }

        fun onFavoriteClick(movie: Movie) {
            listener.onFavoriteClick(movie)
        }
    }

    fun setMovies(movies : Collection<Movie>) {
        this.favoriteMovies.clear()
        this.favoriteMovies.addAll(movies)
        notifyDataSetChanged()
    }

    fun removeMovie(movie: Movie) {
        this.favoriteMovies.remove(movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MovieViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) =
        holder.bind(favoriteMovies[position])


    override fun getItemCount() = favoriteMovies.size

    interface MovieItemListener {
        fun onMovieClick(movie : Movie)
        fun onFavoriteClick(movie: Movie)
    }
}

