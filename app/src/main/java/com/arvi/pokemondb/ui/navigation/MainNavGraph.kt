package com.arvi.pokemondb.ui.navigation

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.arvi.pokemondb.model.Pokemon
import com.arvi.pokemondb.ui.components.CustomerNavigationBar
import com.arvi.pokemondb.ui.components.CustomerNavigationBarItem
import com.arvi.pokemondb.ui.screen.landing.view.LandingView

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainNavGraph(
    mainNavController: NavHostController = rememberNavController(),
) {
    val currentSelectedScreen by mainNavController.currentScreenAsState()
    Scaffold(
        bottomBar = {
            BottomBar(
                currentSelectedScreen = currentSelectedScreen,
                mainNavController = mainNavController,
            )
        }
    ) { paddingValues ->
        val heightToDecrease = 20.dp

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = paddingValues.calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding() - heightToDecrease
                )
        ) {
            CompositionLocalProvider(
                LocalLifecycleOwner provides LocalLifecycleOwner.current
            ) {
                MainNavigation(mainNavController = mainNavController)
            }
        }
    }
}

@Composable
fun BottomBar(
    currentSelectedScreen: MainRoute,
    mainNavController: NavHostController,
) {
    CustomerNavigationBar(
        containerColor = Color.White
    ) {
        CustomerNavigationBarItem(
            selected = currentSelectedScreen == MainRoute.HomeScreen,
            modifier = Modifier.weight(1f),
            icon = Icons.Filled.Home,
            text = "Home",
            onClick = {
                mainNavController.navigate(MainRoute.HomeScreen.route)
            }
        )
        CustomerNavigationBarItem(
            selected = currentSelectedScreen == MainRoute.SettingScreen,
            modifier = Modifier.weight(1f),
            icon = Icons.Filled.Settings,
            text = "Setting",
            onClick = {
                mainNavController.navigate(MainRoute.SettingScreen.route)
            }
        )
    }
}

@Composable
fun MainNavigation(
    mainNavController: NavHostController
) {
    NavHost(
        navController = mainNavController,
        startDestination = MainRoute.HomeScreen.route
    ) {
        composable(route = MainRoute.HomeScreen.route) {
            LandingView(
                mainNavController = mainNavController
            )
        }

        composable(route = MainRoute.DetailScreen.route) {
            val bundle = it.arguments

            val food = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle?.getParcelable("item", Pokemon::class.java)
            } else {
                bundle?.getParcelable("item") as? Pokemon
            }
            Column(modifier = Modifier.fillMaxSize()) {
                if (food == null) {
                    Text(" no detail ")
                } else {
                    Text("Detail :")

                    Text("name: ${food.pokeId}")
                    Text("name: ${food.name}")
                }
            }
        }

        composable(route = MainRoute.FullListScreen.route) {
            val foodList =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    it.arguments?.getParcelableArrayList("list", Pokemon::class.java)
                        ?: emptyList<Pokemon>()
                } else {
                    it.arguments?.getParcelableArrayList("list") ?: emptyList()
                }

            LazyColumn(
                Modifier.fillMaxSize(),
            ) {
                items(count = foodList.size, itemContent = {
                    ListItem(
                        headlineContent = {
                            Column {
                                Text("name: ${foodList[it].pokeId}")
                                Text("name: ${foodList[it].name}")
                            }
                        },
                    )
                })
            }
        }

        composable(route = MainRoute.SettingScreen.route) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(text = "this is SettingScreen screen")
            }
        }
    }
}

@Stable
@Composable
private fun NavController.currentScreenAsState(): State<MainRoute> {
    val selectedItem = remember { mutableStateOf<MainRoute>(MainRoute.HomeScreen) }
    DisposableEffect(key1 = this) {
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            when {
                destination.hierarchy.any { it.route == MainRoute.HomeScreen.route } -> {
                    selectedItem.value = MainRoute.HomeScreen
                }

                destination.hierarchy.any { it.route == MainRoute.SettingScreen.route } -> {
                    selectedItem.value = MainRoute.SettingScreen
                }
            }

        }
        addOnDestinationChangedListener(listener)
        onDispose {
            removeOnDestinationChangedListener(listener)
        }
    }
    return selectedItem
}

@Suppress("unused")
fun NavController.navigate(
    route: String,
    args: Bundle,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null
) {
    val nodeId = graph.findNode(route = route)?.id
    if (nodeId != null) {
        navigate(nodeId, args, navOptions, navigatorExtras)
    }
}
