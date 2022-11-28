package com.example.ancient_pokedex.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.ancient_pokedex.paging.PokemonPagingAdapter.*
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ancient_pokedex.databinding.PokemonLayoutBinding
import com.example.ancient_pokedex.model.Result

class PokemonPagingAdapter : PagingDataAdapter<Result, PokemonViewHolder>(diffCallback) {

    inner class PokemonViewHolder(val binding: PokemonLayoutBinding):
    RecyclerView.ViewHolder(binding.root)

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
        holder.binding.apply {
            textView.text = "${currentItem?.name}"


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder(PokemonLayoutBinding.inflate(LayoutInflater.from(parent.context),
        parent,
        false))
    }
}