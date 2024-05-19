package com.arvi.pokemondb.ui.screen.landing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arvi.domain.landing.usecase.GetPokemonListUseCase
import com.arvi.pokemondb.ui.screen.landing.model.PokeListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class LandingViewModel @Inject constructor(
    private val getPokemonListUseCase: GetPokemonListUseCase
) : ViewModel() {
    private val _uiState: MutableStateFlow<PokeListUiState> = MutableStateFlow(PokeListUiState())
    val uiState: StateFlow<PokeListUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getPokemonListUseCase().collect {
                _uiState.value = PokeListUiState(pokemonList = it)
            }
        }
    }
}
