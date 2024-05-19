package com.arvi.pokemondb.ui.screen.landing.view

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.arvi.domain.landing.models.Pokemon
import com.arvi.pokemondb.ui.screen.landing.LandingViewModel
import com.arvi.pokemondb.ui.screen.landing.view.LandingView.LOADING_INDICATOR_SIZE
import java.util.Locale
import java.util.UUID

@Composable
fun LandingView(
    modifier: Modifier = Modifier,
    @Suppress("UNUSED_PARAMETER") mainNavController: NavHostController = rememberNavController(),
    viewModel: LandingViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val isLoading by remember { mutableStateOf(false) }

    PokemonList(
        modifier = modifier,
        pokemonList = uiState.pokemonList.pokemonList,
        isLoading = isLoading,
    )
}

@Composable
fun PokemonList(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    pokemonList: List<Pokemon>,
) {
    if (isLoading) {
        CircularProgressIndicator(
            modifier = modifier.width(LOADING_INDICATOR_SIZE.dp),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    } else {
        LazyColumn(modifier = modifier) {
            items(pokemonList) { pokemon ->
                PokemonRow(
                    modifier = modifier,
                    pokemonName = pokemon.pokeName.replaceFirstChar { char ->
                        if (char.isLowerCase()) char.titlecase(Locale.getDefault()) else char.toString()
                    }
                )
            }
        }
    }
}

private object LandingView {
    const val LOADING_INDICATOR_SIZE = 64
}

@Preview(
    showBackground = true,
    device = "id:pixel_7"
)
@Composable
fun LandingViewPreview() {
    PokemonList(
        pokemonList = listOf(
            Pokemon(UUID.randomUUID().toString(), "Charmander"),
            Pokemon(UUID.randomUUID().toString(), "Bulbasaur")
        ),
        isLoading = false
    )
}
