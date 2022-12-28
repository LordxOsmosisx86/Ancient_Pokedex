package com.example.ancient_pokedex.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.ancient_pokedex.R
import com.example.ancient_pokedex.databinding.ActivityMainBinding
import com.example.ancient_pokedex.interfaces.PokemonService
import com.example.ancient_pokedex.ui.fragments.DexHomeFragment
import com.example.ancient_pokedex.ui.fragments.PokemonPageFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val dexHomeFragment : DexHomeFragment = DexHomeFragment()
    val pokemonPageFragment : PokemonPageFragment = PokemonPageFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}