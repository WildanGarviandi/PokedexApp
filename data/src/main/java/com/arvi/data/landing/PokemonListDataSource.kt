package com.arvi.data.landing

import android.content.Context
import com.arvi.domain.landing.models.Pokelist
import com.arvi.domain.landing.models.Pokemon
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokemonListDataSource @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    val pokemonList: Flow<Pokelist> = flow {
        emit(Pokelist(listOf(
            Pokemon(pokeId = "id1", pokeName = "Charmander"),
            Pokemon(pokeId = "id2", pokeName = "Bulbasaur"),
            Pokemon(pokeId = "id3", pokeName = "Squirtle")
        )))
    }
}
