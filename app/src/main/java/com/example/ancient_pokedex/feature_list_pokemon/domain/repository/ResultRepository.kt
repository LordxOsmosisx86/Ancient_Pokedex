package com.example.ancient_pokedex.feature_list_pokemon.data.repository

import com.example.ancient_pokedex.feature_list_pokemon.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface ResultRepository {

    fun getResults() : Flow<List<Result>>

    suspend fun getResultById(dexNum : Int): Result?
    suspend fun insertResult(result : Result)
    suspend fun deleteNote(result : Result)
}