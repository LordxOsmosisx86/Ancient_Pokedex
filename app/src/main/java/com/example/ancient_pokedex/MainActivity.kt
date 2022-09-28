package com.example.ancient_pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.ancient_pokedex.interfaces.PokemonService
import com.example.ancient_pokedex.model.Pokemon
import com.example.ancient_pokedex.model.Result
import com.example.ancient_pokedex.model.RetrofitInstance
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val retrofitService =RetrofitInstance
            .getRetrofitInstance()
            .create(PokemonService::class.java)
        val responseLiveData:LiveData<Response<Pokemon>> = liveData {
            val response = retrofitService.getPokemon()
            emit(response)
        }
        responseLiveData.observe(this) {
            val pokemon = it.body()?.results
            val iterator = pokemon?.listIterator()
            if (iterator!= null) {
                while (iterator.hasNext()) {
                    val pkm = iterator.next()
                    Log.i("Hourani", pkm.name)
                }
            }
            else
                Log.i("Hourani", "No Data")
        }
    }
}