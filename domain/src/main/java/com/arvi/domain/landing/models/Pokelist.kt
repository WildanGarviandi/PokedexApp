package com.arvi.domain.landing.models

data class Pokelist(
    val pokemonList: List<Pokemon> = listOf()
)

data class Pokemon(
    val pokeId: String = "",
    val pokeName: String = ""
)
