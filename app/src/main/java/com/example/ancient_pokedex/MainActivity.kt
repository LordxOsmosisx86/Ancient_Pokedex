package com.example.ancient_pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.ancient_pokedex.databinding.ActivityMainBinding
import com.example.ancient_pokedex.interfaces.PokemonService
import com.example.ancient_pokedex.model.Pokemon
import com.example.ancient_pokedex.model.Result
import com.example.ancient_pokedex.model.RetrofitInstance
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val retrofitService =RetrofitInstance
            .getRetrofitInstance()
            .create(PokemonService::class.java)
        var responseLiveData: LiveData<Response<Pokemon>> = liveData {
            var i = 0
            var response = retrofitService.getNextPage(i)
            while(response.body()?.next?.isNotEmpty() == true) {
                emit(response)
                i+=20
                response = retrofitService.getNextPage(i)
            }
        }
        responseLiveData.observe(this) {
            val pokemon = it.body()?.results
            val iterator = pokemon?.listIterator()
            if (iterator!= null) {
                while (iterator.hasNext()) {
                    val pkm = iterator.next()
                    val result = " " + "Pokemon Name: ${pkm.name}" + "\n\n"
                    binding.textView.append(result)
                }
            }
            else
                Log.i("Hourani", "No Data")
        }
    }
}