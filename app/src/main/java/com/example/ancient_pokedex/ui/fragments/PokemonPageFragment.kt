package com.example.ancient_pokedex.ui.fragments

import android.R
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.core.view.get
import androidx.fragment.app.activityViewModels
import com.example.ancient_pokedex.databinding.FragmentPokemonPageBinding
import com.example.ancient_pokedex.models.EggGroup
import com.example.ancient_pokedex.ui.PokemonViewModel
import com.example.ancient_pokedex.ui.activities.MainActivity
import com.example.ancient_pokedex.utils.Constants
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PokemonPageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class PokemonPageFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val sharedPokemonViewMode : PokemonViewModel by activityViewModels()
    private var _binding: FragmentPokemonPageBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPokemonPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadSelectedPokemonData()
    }

    private fun loadSelectedPokemonData() {
        //Todo: Add the rest of the field here and organize the view to be a bit nicer.
        //Log.d("Hourani", "Here is the viewID: ${} ")
        val listView = binding.pkmEggGroup
        val adapter : ArrayAdapter<EggGroup> = ArrayAdapter(activity!!.applicationContext, android.R.layout.simple_list_item_1, sharedPokemonViewMode.eggGroup!!)
        Picasso.get().load(Constants.pokemonArtWorkURI+sharedPokemonViewMode.clickedPokemonData?.id.toString()+".png").into(binding.pokemonOfficialArt)
        binding.pokemonInfoName.text = sharedPokemonViewMode.clickedPokemonData?.name
        binding.baseHappiness.text = sharedPokemonViewMode.clickedPokemonSpeciesData?.baseHappiness.toString()
        binding.captureRate.text = sharedPokemonViewMode.clickedPokemonSpeciesData?.captureRate.toString()
        binding.pkmColor.text = sharedPokemonViewMode.clickedPokemonSpeciesData?.color?.name.toString()
        listView.adapter = adapter


    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PokemonPageFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PokemonPageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}