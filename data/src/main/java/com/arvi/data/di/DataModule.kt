package com.arvi.data.di

import com.arvi.data.network.PokemonService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {
    @Suppress("FunctionOnlyReturningConstant")
    @Provides
    fun provideBaseUrl(): String = "https://pokeapi.co/api/v2/"

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String): Retrofit {
        val logging: HttpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BASIC)
        val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        if (!httpClient.interceptors().contains(logging)) {
            httpClient.addInterceptor(logging)
        }
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providePokemonService(retrofit: Retrofit): PokemonService =
        retrofit.create(PokemonService::class.java)
}
