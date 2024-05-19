package com.arvi.data.landing

import com.arvi.data.network.PokemonService
import com.arvi.domain.landing.models.Pokelist
import com.arvi.domain.landing.models.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import java.util.UUID
import javax.inject.Inject

class PokemonListDataSource @Inject constructor(
    private val apiService: PokemonService
) {
    private val pokemonId: String
        get() = UUID.randomUUID().toString()

    val pokemonList: Flow<Pokelist> = flow {
        val pokeList = apiService.getPokemonList(SIZE_REQUEST, 0).pokemonList
        pokeList.map {
            Pokemon(
                pokeId = pokemonId,
                pokeName = it.pokeName,
                pokeUrl = it.pokeUrl,
            )
        }
        log().d("Pokemon list size : ${pokeList.size}")
        emit(Pokelist(pokemonList = pokeList))
    }

    companion object {
        private const val LOG_TAG = "#PokemonListDataSource"
        private const val SIZE_REQUEST = 20
        private fun log() = Timber.tag(LOG_TAG)
    }
}
