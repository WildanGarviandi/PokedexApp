package com.arvi.pokemondb.ui.navigation

sealed class AppRoute(val route: String) {
    data object MainScreen : AppRoute("main_screen")
}

sealed class MainRoute(val route: String) {
    data object HomeScreen : MainRoute("home_screen")
    data object SettingScreen : MainRoute("setting_screen")
    data object DetailScreen : MainRoute("detail_screen")
    data object FullListScreen: MainRoute("full_list_screen")
}
