package com.example.ancient_pokedex.interfaces

import androidx.lifecycle.MutableLiveData
import com.example.ancient_pokedex.models.Pokemon
import com.example.ancient_pokedex.models.PokemonData
import com.example.ancient_pokedex.models.PokemonSpeciesData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {
    @GET("pokemon")
    suspend fun getNextPage(
        @Query(value ="offset") pageNum:Int
    ) : Response<Pokemon>

    @GET("pokemon/{name}")
    suspend fun getPokemonData(
        @Path(value = "name") name:String
    ) : Response<PokemonData>

    @GET("pokemon-species/{name}")
    suspend fun getPokemonSpeciesData(
        @Path(value = "name") name:String
    ) : Response<PokemonSpeciesData>
}