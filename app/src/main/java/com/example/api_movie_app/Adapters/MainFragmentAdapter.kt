package com.example.api_movie_app.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.api_movie_app.Fragments.MainFragment
import com.example.api_movie_app.data.models.Movie
import com.example.api_movie_app.databinding.FragmentMainBinding
import com.example.api_movie_app.databinding.ItemMovieMainpageBinding

class MainFragmentAdapter(private val listener: MainFragment) :
    RecyclerView.Adapter<MainFragmentAdapter.MovieViewHolder>() {

    private val allMovies = ArrayList<Movie>()

    class MovieViewHolder(private val itemBinding: FragmentMainBinding,
                             private val listener: MainFragment
    )
        : RecyclerView.ViewHolder(itemBinding.root),
        View.OnClickListener {

        private lateinit var movie: Movie

        init {
            itemBinding.root.setOnClickListener(this)
        }

        fun bind(item: Movie) {
            this.movie = item


            Glide.with(itemBinding.root)
                .load(item.image)
                .into(itemBinding.image)
        }

        override fun onClick(v: View?) {
            listener.onMovieClick(movie)
        }
    }

    fun setMovies(movies : Collection<Movie>) {
        this.allMovies.clear()
        this.allMovies.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val binding = FragmentMainBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MovieViewHolder(binding, listener)
    }


    override fun getItemCount() = allMovies.size

    interface MovieItemListener {
        fun onMovieClick(movie : Movie)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(allMovies[position])
    }

}