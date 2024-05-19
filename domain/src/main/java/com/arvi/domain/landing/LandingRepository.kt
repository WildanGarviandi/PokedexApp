package com.arvi.domain.landing

import com.arvi.domain.landing.models.Pokelist
import kotlinx.coroutines.flow.Flow

interface LandingRepository {
    fun getPokemonList(): Flow<Pokelist>
}
