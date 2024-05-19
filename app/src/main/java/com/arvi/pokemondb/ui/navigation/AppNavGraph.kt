package com.arvi.pokemondb.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavGraph(
    appNavController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = appNavController,
        startDestination = AppRoute.MainScreen.route
    ) {
        composable(route = AppRoute.MainScreen.route) {
            MainNavGraph()
        }
    }
}
