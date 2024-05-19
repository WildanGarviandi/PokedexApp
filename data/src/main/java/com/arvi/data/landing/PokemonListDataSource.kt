package com.arvi.data.landing

import android.content.Context
import com.arvi.domain.landing.models.Pokelist
import com.arvi.domain.landing.models.Pokemon
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class PokemonListDataSource @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    val pokemonList: Flow<Pokelist> = flow {
        log().d("pokemonList : ${context.applicationContext.packageName}")
        emit(Pokelist(listOf(
            Pokemon(pokeId = "id1", pokeName = "Charmander"),
            Pokemon(pokeId = "id2", pokeName = "Bulbasaur"),
            Pokemon(pokeId = "id3", pokeName = "Squirtle")
        )))
    }

    companion object {
        private const val LOG_TAG = "#PokemonListDataSource"
        private fun log() = Timber.tag(LOG_TAG)
    }
}
