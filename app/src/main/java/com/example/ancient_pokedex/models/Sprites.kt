package com.example.ancient_pokedex.models


import com.google.gson.annotations.SerializedName

data class Sprites(
    @SerializedName("other")
    val other: Other
)