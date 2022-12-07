package com.example.ancient_pokedex.paging

import android.speech.RecognitionListener
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.paging.PagingDataAdapter
import com.example.ancient_pokedex.paging.PokemonPagingAdapter.*
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ancient_pokedex.MainActivity
import com.example.ancient_pokedex.databinding.PokemonLayoutBinding
import com.example.ancient_pokedex.interfaces.PokemonRecyclerViewInterface
import com.example.ancient_pokedex.model.Result
import com.example.ancient_pokedex.utils.Constants
import com.squareup.picasso.Picasso

class PokemonPagingAdapter(private val listener: PokemonRecyclerViewInterface):
    PagingDataAdapter<Result, PokemonViewHolder>(diffCallback) {

    inner class PokemonViewHolder(val binding: PokemonLayoutBinding):
    RecyclerView.ViewHolder(binding.root), OnClickListener {
        init {
            binding.root.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position: Int = absoluteAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClicked(position)
            }
        }
    }
    companion object {
        val diffCallback = object: DiffUtil.ItemCallback<Result>(){
            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val currentItem = getItem(position)
        if(currentItem?.id != 0) {
            holder.binding.apply {
                pokedexNumber.text = "${currentItem?.id}"
                pokemonName.text = "${currentItem?.name?.replaceFirstChar { it.uppercase() }}"
                Picasso.get().load(Constants.pokemonSpritesURI+currentItem?.name+".png").into(pokemonSprite)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder(PokemonLayoutBinding.inflate(LayoutInflater.from(parent.context),
        parent,
        false))
    }
}
