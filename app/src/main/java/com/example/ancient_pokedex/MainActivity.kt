package com.example.ancient_pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.ancient_pokedex.databinding.ActivityMainBinding
import com.example.ancient_pokedex.paging.PokemonPagingAdapter
import com.example.ancient_pokedex.ui.PokemonViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mAdapter: PokemonPagingAdapter
    private val viewModel: PokemonViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)

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