package com.example.ancient_pokedex.interfaces

import com.example.ancient_pokedex.model.Pokemon
import com.example.ancient_pokedex.model.Result
import retrofit2.Response
import retrofit2.http.GET

interface PokemonService {
    @GET("pokemon-species")
    suspend fun getPokemon() : Response<Pokemon>
}