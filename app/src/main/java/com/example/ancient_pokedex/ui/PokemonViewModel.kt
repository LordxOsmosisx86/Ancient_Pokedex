package com.example.ancient_pokedex.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.ancient_pokedex.paging.PokemonPagingSource
import com.example.ancient_pokedex.interfaces.PokemonService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel()
class PokemonViewModel
@Inject constructor(private val pokemonService: PokemonService): ViewModel(){
    val listData = Pager(PagingConfig(pageSize = 1)){
        PokemonPagingSource(pokemonService)
    }.flow
}