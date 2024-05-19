package com.arvi.pokemondb.di.landing

import com.arvi.domain.landing.LandingRepository
import com.arvi.domain.landing.usecase.GetPokemonListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class LandingDomainModule {
    @Provides
    fun providesPokemonListUseCase(repository: LandingRepository) =
        GetPokemonListUseCase(repository)
}
