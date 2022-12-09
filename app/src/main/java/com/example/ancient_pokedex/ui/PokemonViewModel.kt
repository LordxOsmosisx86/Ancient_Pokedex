package com.example.ancient_pokedex.ui

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.ancient_pokedex.paging.PokemonPagingSource
import com.example.ancient_pokedex.interfaces.PokemonService
import com.example.ancient_pokedex.models.PokemonData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel()
class PokemonViewModel
@Inject constructor(private val pokemonService: PokemonService): ViewModel(){
    val listData = Pager(PagingConfig(pageSize = 10)){
        PokemonPagingSource(pokemonService)
    }.flow
    var positionNum : String? = null
    var clickedPokemonData : PokemonData? = null

    suspend fun getPokemonData(pokemonID : Int) {
        clickedPokemonData = pokemonService.getPokemonData(pokemonID.toString()).body()
    }


}