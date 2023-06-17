package com.example.api_movie_app.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.api_movie_app.Fragments.MainFragment
import com.example.api_movie_app.data.models.Cocktail
import com.example.api_movie_app.databinding.ItemCocktailMainpageBinding

class MainFragmentAdapter(private val listener: MainFragment) :
    RecyclerView.Adapter<MainFragmentAdapter.CocktailViewHolder>() {

    private val allCocktails = ArrayList<Cocktail>()

    class CocktailViewHolder(private val itemBinding: ItemCocktailMainpageBinding,
                             private val listener: MainPage
    )
        : RecyclerView.ViewHolder(itemBinding.root),
        View.OnClickListener {

        private lateinit var cocktail: Cocktail

        init {
            itemBinding.root.setOnClickListener(this)
        }

        fun bind(item: Cocktail) {
            this.cocktail = item


            Glide.with(itemBinding.root)
                .load(item.strDrinkThumb)
                .into(itemBinding.image)
        }

        override fun onClick(v: View?) {
            listener.onCocktailClick(cocktail)
        }
    }

    fun setCocktails(cocktails : Collection<Cocktail>) {
        this.allCocktails.clear()
        this.allCocktails.addAll(cocktails)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CocktailViewHolder {
        val binding = ItemCocktailMainpageBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CocktailViewHolder(binding, listener)
    }


    override fun getItemCount() = allCocktails.size

    interface CocktailItemListener {
        fun onCocktailClick(cocktail : Cocktail)
    }

    override fun onBindViewHolder(holder: CocktailViewHolder, position: Int) {
        holder.bind(allCocktails[position])
    }

}