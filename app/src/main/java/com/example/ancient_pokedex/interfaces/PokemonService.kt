package com.example.ancient_pokedex.interfaces

import com.example.ancient_pokedex.model.OfficialArtwork
import com.example.ancient_pokedex.model.Pokemon
import com.example.ancient_pokedex.model.PokemonData
import com.example.ancient_pokedex.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {
    @GET(Constants.END_POINT)
    suspend fun getNextPage(
        @Query(value ="offset") pageNum:Int
    ) : Response<Pokemon>

    @GET(Constants.END_POINT+"/{name}")
    suspend fun getPokemonData(
        @Path(value ="name") name:String
    ) : Response<PokemonData>

    @GET("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/{id}.png")
    suspend fun getPokemonOfficialArtwork(
        @Path(value = "id") id:Int
    ) : Response<OfficialArtwork>
}