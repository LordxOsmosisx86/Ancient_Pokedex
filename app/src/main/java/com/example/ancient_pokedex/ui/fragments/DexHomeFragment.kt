package com.example.ancient_pokedex.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.ancient_pokedex.databinding.FragmentDexHomeBinding
import com.example.ancient_pokedex.interfaces.PokemonRecyclerViewInterface
import com.example.ancient_pokedex.interfaces.PokemonService
import com.example.ancient_pokedex.models.PokemonData
import com.example.ancient_pokedex.paging.PokemonPagingAdapter
import com.example.ancient_pokedex.ui.PokemonViewModel
import com.example.ancient_pokedex.ui.activities.MainActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
/**
 *
 * A simple [Fragment] subclass.
 * Use the [DexHomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
//@AndroidEntryPoint

class DexHomeFragment() : Fragment(), PokemonRecyclerViewInterface {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val sharedPokemonViewMode: PokemonViewModel by activityViewModels()
    private var _binding: FragmentDexHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var pokemonAdapter: PokemonPagingAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDexHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("Hourani", "Main Frag: ",)

        Toast.makeText(context, "Testing", Toast.LENGTH_SHORT).show()
        setupRv()
        loadingData()
    }

    private fun loadingData() {
        lifecycleScope.launch {
            Log.d("Hello", "In coroutine")

            sharedPokemonViewMode.listData.collect { pagingData ->
                Log.d("Hourani", "p${pokemonAdapter}: ")
                pokemonAdapter?.submitData(pagingData)
            }
        }
    }

    private fun setupRv() {
        pokemonAdapter = PokemonPagingAdapter(this)
        binding?.recyclerView?.apply {
            Log.d("Hello", "In binding")
            layoutManager = StaggeredGridLayoutManager(
                1, StaggeredGridLayoutManager.VERTICAL
            )
            adapter = pokemonAdapter
            setHasFixedSize(true)
        }
    }

    override fun onItemClicked(position: Int) {
        sharedPokemonViewMode.positionNum = pokemonAdapter.peek(position)?.id.toString()
        //TODO: Fix scope and make sure the next fragment doesn't launch until all the data is retrieved.
        GlobalScope.launch {
            sharedPokemonViewMode.getPokemonData(sharedPokemonViewMode.positionNum!!.toInt())
            (activity as MainActivity).replaceFragment(PokemonPageFragment())
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DexHomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DexHomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}