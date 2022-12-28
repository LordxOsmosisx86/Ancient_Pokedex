package com.example.ancient_pokedex.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.example.ancient_pokedex.databinding.FragmentPokemonPageBinding
import com.example.ancient_pokedex.models.PokemonData
import com.example.ancient_pokedex.ui.viewmodel.PokemonViewModel
import com.example.ancient_pokedex.utils.Constants
import com.squareup.picasso.Picasso
import kotlinx.coroutines.*
import okhttp3.internal.wait

class PokemonPageFragment() : Fragment() {
    private val sharedPokemonViewModel : PokemonViewModel by activityViewModels()
    private var _binding: FragmentPokemonPageBinding? = null
    private val binding get() = _binding!!
    private val TAG = "PokemonPageFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPokemonPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadSelectedPokemonData()
    }

    private fun loadSelectedPokemonData() {
        Picasso.get().load(Constants.pokemonArtWorkURI+"${sharedPokemonViewModel.pokemonInfo?.id}.png").into(binding.pokemonOfficialArt)
        binding.pokemonInfoName.text = sharedPokemonViewModel.pokemonInfo?.name
        binding.text = sharedPokemonViewModel.pokemonSpeciesInfo?.baseHappiness
        binding.captureRate.text = sharedPokemonViewModel.pokemonSpeciesInfo?.captureRate.toString()
    }

}