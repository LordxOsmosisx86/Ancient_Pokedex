package com.example.ancient_pokedex.feature_list_pokemon.data.data_source

import androidx.room.Dao
import androidx.room.Query
import com.example.ancient_pokedex.feature_list_pokemon.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {
    @Query("SELECT * FROM Pokemon ")
    fun getPokemon(): Flow<List<Pokemon>>

    suspend fun getPokemonByDexNum(dexNum : Int): Pokemon?
}