package com.arvi.data.landing

import com.arvi.data.di.dispatcher.IoDispatcher
import com.arvi.domain.landing.LandingRepository
import com.arvi.domain.landing.models.Pokelist
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LandingRepositoryImpl @Inject constructor(
    private val dataSource: PokemonListDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): LandingRepository {
    override fun getPokemonList(): Flow<Pokelist> = dataSource.pokemonList.flowOn(ioDispatcher)
}
