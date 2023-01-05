package com.example.ancient_pokedex.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.ancient_pokedex.paging.PokemonPagingSource
import com.example.ancient_pokedex.interfaces.PokemonService
import com.example.ancient_pokedex.models.PokemonData
import com.example.ancient_pokedex.models.PokemonSpeciesData
import com.example.ancient_pokedex.repositories.PokemonRepository
import com.example.ancient_pokedex.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel
@Inject constructor(
    private val pokemonService: PokemonService,
    private val pokemonRepository: PokemonRepository): ViewModel() {

    val TAG = "PokemonViewModel"
    var pokemonMutableLiveData : MutableLiveData<Resource<PokemonData>> = MutableLiveData()
    var pokemonSpeciesMutableLiveData : MutableLiveData<Resource<PokemonSpeciesData>> = MutableLiveData()

    val listData = Pager(PagingConfig(pageSize = 10)){
        PokemonPagingSource(pokemonService)
    }.flow

    fun getPokemonData(name: String) = viewModelScope.launch {
        pokemonMutableLiveData.postValue(Resource.Loading())
        val pokemonDataResponse = pokemonRepository.getPokemonData(name)
        val pokemonSpeciesDataResponse : Response<PokemonSpeciesData> = pokemonRepository.getPokemonSpeciesData(name)
        pokemonMutableLiveData.postValue(handlePokemonData(pokemonDataResponse))
        pokemonSpeciesMutableLiveData.postValue(handlePokemonSpeciesData(pokemonSpeciesDataResponse))
    }

    private fun handlePokemonData(response: Response<PokemonData>) : Resource<PokemonData> {
        Resource.Loading<PokemonData>()
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handlePokemonSpeciesData(response: Response<PokemonSpeciesData>) : Resource<PokemonSpeciesData> {
        Resource.Loading<PokemonSpeciesData>()
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}
