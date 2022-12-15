package com.example.ancient_pokedex.models


import com.google.gson.annotations.SerializedName

data class EggGroup(
    @SerializedName("name")
    val name: String
    //@SerializedName("url")
    //val url: String
)