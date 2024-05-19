package com.arvi.domain.landing.usecase

import com.arvi.domain.landing.LandingRepository

class GetPokemonListUseCase(private val repository: LandingRepository) {
    operator fun invoke() = repository.getPokemonList()
}
