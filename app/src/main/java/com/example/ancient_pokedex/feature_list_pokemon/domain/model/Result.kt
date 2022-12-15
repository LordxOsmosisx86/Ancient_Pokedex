package com.example.ancient_pokedex.models


import com.google.gson.annotations.SerializedName

data class Result(
    var id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)