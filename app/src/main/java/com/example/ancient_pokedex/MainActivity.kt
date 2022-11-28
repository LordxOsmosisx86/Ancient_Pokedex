package com.example.ancient_pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.ancient_pokedex.databinding.ActivityMainBinding
import com.example.ancient_pokedex.paging.PokemonPagingAdapter
import com.example.ancient_pokedex.ui.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mAdapter: PokemonPagingAdapter
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
                mAdapter.submitData(pagingData)
            }
        }
    }

    private fun setupRv(){
        mAdapter = PokemonPagingAdapter()
        binding.recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(
                2, StaggeredGridLayoutManager.VERTICAL
            )

            adapter = mAdapter
            setHasFixedSize(true)
        }
    }
}