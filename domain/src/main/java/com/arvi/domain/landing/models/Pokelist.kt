package com.arvi.domain.landing.models

import com.google.gson.annotations.SerializedName

data class Pokelist(
    @SerializedName("results")
    val pokemonList: List<Pokemon> = listOf()
)

data class Pokemon(
    val pokeId: String = "",

    @SerializedName("name")
    val pokeName: String = "",

    @SerializedName("url")
    val pokeUrl: String = ""
)
