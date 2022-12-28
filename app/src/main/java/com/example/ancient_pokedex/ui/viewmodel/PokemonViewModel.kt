package com.example.ancient_pokedex.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.ancient_pokedex.paging.PokemonPagingSource
import com.example.ancient_pokedex.interfaces.PokemonService
import com.example.ancient_pokedex.models.PokemonData
import com.example.ancient_pokedex.models.PokemonSpeciesData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel()
class PokemonViewModel
@Inject constructor(private val pokemonService: PokemonService): ViewModel(){
    var pokemonInfo: PokemonData? = null
    var pokemonSpeciesInfo: PokemonSpeciesData? = null
    val listData = Pager(PagingConfig(pageSize = 10)){
        PokemonPagingSource(pokemonService)
    }.flow

    suspend fun getSelectedPokemonInfo(name : String) {
            when(pokemonService.getPokemonData(name).code()) {
                200 -> {
                    pokemonInfo = pokemonService.getPokemonData(name).body()
                }
                else -> error("Error")
        }
    }
    suspend fun getSelectedPokemonSpeciesInfo(name : String) {
        when(pokemonService.getPokemonData(name).code()) {
            200 -> {
                pokemonSpeciesInfo = pokemonService.getPokemonSpeciesData(name).body()
            }
            else -> error("Error")
        }
    }

}