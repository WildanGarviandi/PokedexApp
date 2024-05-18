package com.arvi.pokemondb.ui.screen.landing.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun LandingView(
    mainNavController: NavHostController = rememberNavController(),
) {
    Text(text = "Home screen")
}

@Preview
@Composable
fun LandingViewPreview() {

}
