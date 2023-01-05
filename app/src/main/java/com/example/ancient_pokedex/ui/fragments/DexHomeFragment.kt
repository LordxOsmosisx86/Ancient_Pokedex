package com.example.ancient_pokedex.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.ancient_pokedex.R
import com.example.ancient_pokedex.databinding.FragmentDexHomeBinding
import com.example.ancient_pokedex.interfaces.PokemonRecyclerViewInterface
import com.example.ancient_pokedex.paging.PokemonPagingAdapter
import com.example.ancient_pokedex.ui.viewmodel.PokemonViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.*

class DexHomeFragment : Fragment(R.layout.fragment_dex_home), PokemonRecyclerViewInterface {
    private val sharedPokemonViewModel: PokemonViewModel by activityViewModels()
    private var _binding: FragmentDexHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var pokemonAdapter: PokemonPagingAdapter
    val TAG : String = "DexHomeFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDexHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRv()
        loadingData()
    }

    private fun loadingData() {
        lifecycleScope.launch {
            sharedPokemonViewModel.listData.collect { pagingData ->
                Log.d("Hourani", "p${pokemonAdapter}: ")
                pokemonAdapter.submitData(pagingData)
            }
        }
    }

    private fun setupRv() {
        pokemonAdapter = PokemonPagingAdapter(this)
        binding.recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(
                1, StaggeredGridLayoutManager.VERTICAL
            )
            adapter = pokemonAdapter
            setHasFixedSize(true)
        }
    }

    override fun onItemClicked(position: Int) {
        val selectedPokemon = pokemonAdapter.peek(position)?.name.toString()
        sharedPokemonViewModel.getPokemonData(selectedPokemon)
        findNavController().navigate(R.id.action_dexHomeFragment_to_pokemonPageFragment)
    }
}
