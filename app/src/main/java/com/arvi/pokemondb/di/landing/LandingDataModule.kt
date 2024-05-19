package com.arvi.pokemondb.di.landing

import com.arvi.data.landing.LandingRepositoryImpl
import com.arvi.domain.landing.LandingRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class LandingDataModule {
    @Singleton
    @Binds
    abstract fun bindLandingRepository(impl: LandingRepositoryImpl): LandingRepository
}
