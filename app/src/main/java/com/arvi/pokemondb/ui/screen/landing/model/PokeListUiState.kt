package com.arvi.pokemondb.ui.screen.landing.model

import com.arvi.domain.landing.models.Pokelist
import com.arvi.domain.landing.models.Pokemon

data class PokeListUiState(
    val pokemonList: Pokelist = Pokelist(),
    val isLoading: Boolean = false
)
