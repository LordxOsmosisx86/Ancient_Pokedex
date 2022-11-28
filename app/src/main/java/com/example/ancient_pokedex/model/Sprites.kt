package com.example.ancient_pokedex.model


import com.google.gson.annotations.SerializedName

data class Sprites(
    @SerializedName("other")
    val other: Other,
)