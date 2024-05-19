package com.arvi.pokemondb.ui.screen.landing.view

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.arvi.pokemondb.ui.screen.landing.LandingViewModel

@Composable
fun LandingView(
    modifier: Modifier = Modifier,
    @Suppress("UNUSED_PARAMETER") mainNavController: NavHostController = rememberNavController(),
    viewModel: LandingViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    @Suppress("UNUSED_VARIABLE") var isLoading by remember { mutableStateOf(false) }

    LazyColumn(modifier = modifier) {
        items(uiState.pokemonList.pokemonList) {
            Text(text = it.pokeId)
            Text(text = it.pokeName)
        }
    }
}

@Preview
@Composable
fun LandingViewPreview() {
    LandingView()
}
