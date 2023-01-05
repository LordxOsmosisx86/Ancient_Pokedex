package com.example.ancient_pokedex.repositories

import com.example.ancient_pokedex.interfaces.PokemonService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


class PokemonRepository
@Inject constructor(private val pokemonService: PokemonService){
    suspend fun getPokemonData(name : String)  =
        pokemonService.getPokemonData(name)

    suspend fun getPokemonSpeciesData(name : String) =
        pokemonService.getPokemonSpeciesData(name)
}



