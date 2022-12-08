package com.example.ancient_pokedex.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.ancient_pokedex.R
import com.example.ancient_pokedex.paging.PokemonPagingSource
import com.example.ancient_pokedex.interfaces.PokemonService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel()
class PokemonViewModel
@Inject constructor(private val pokemonService: PokemonService): ViewModel(){
    val listData = Pager(PagingConfig(pageSize = 10)){
        PokemonPagingSource(pokemonService)
    }.flow
}