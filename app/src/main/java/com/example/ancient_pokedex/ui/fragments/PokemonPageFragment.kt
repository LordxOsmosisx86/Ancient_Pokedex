package com.example.ancient_pokedex.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import com.example.ancient_pokedex.databinding.FragmentPokemonPageBinding
import com.example.ancient_pokedex.ui.viewmodel.PokemonViewModel
import com.example.ancient_pokedex.utils.Constants
import com.squareup.picasso.Picasso
import dagger.hilt.android.scopes.ViewModelScoped


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
            sharedPokemonViewModel.pokemonMutableLiveData?.observe(viewLifecycleOwner) {
                Picasso.get().load(Constants.pokemonArtWorkURI + "${it.data?.id}.png")
                    .into(binding.pokemonOfficialArt)
                binding.pokemonInfoName.text = it.data?.name
            }
            sharedPokemonViewModel.pokemonSpeciesMutableLiveData?.observe(viewLifecycleOwner) {
                binding.baseHappiness.text = it.data?.baseHappiness.toString()
                binding.captureRate.text = it.data?.captureRate.toString()
                binding.pkmColor.text = it.data?.color?.name
            }
    }
}