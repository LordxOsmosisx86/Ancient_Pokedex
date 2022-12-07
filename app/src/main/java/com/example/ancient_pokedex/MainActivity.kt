package com.example.ancient_pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView.OnItemClickListener
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.get
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.ancient_pokedex.databinding.ActivityMainBinding
import com.example.ancient_pokedex.interfaces.PokemonRecyclerViewInterface
import com.example.ancient_pokedex.paging.PokemonPagingAdapter
import com.example.ancient_pokedex.ui.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
@AndroidEntryPoint
class MainActivity : AppCompatActivity(), PokemonRecyclerViewInterface {
    private lateinit var binding: ActivityMainBinding
    private lateinit var pokemonAdapter: PokemonPagingAdapter
    private val viewModel: PokemonViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupRv()
        loadingData()
    }

    private fun loadingData() {
        lifecycleScope.launch{
            viewModel.listData.collect{ pagingData->
                pokemonAdapter.submitData(pagingData)
            }
        }
    }

    private fun setupRv(){
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
        Toast.makeText(this, "${pokemonAdapter.peek(position)?.name} clicked", Toast.LENGTH_SHORT).show()
    }
}