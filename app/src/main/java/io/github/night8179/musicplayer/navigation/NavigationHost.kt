package io.github.night8179.musicplayer.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import io.github.night8179.feature.screen.FavouriteScreen
import io.github.night8179.feature.screen.HomeScreen
import io.github.night8179.feature.screen.TrackcardScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    Scaffold(bottomBar = {
        if (navBackStackEntry?.destination?.hasRoute<TrackCard>() == false) {
            NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
                NavigationBarItem(
                    selected = navBackStackEntry?.destination?.hasRoute<Home>() == true,
                    onClick = {
                        navController.navigate(Home) {
                            launchSingleTop = true
                            popUpTo<Home> {
                                inclusive = false
                                saveState = true
                            }
                            restoreState = true
                        }
                    },
                    icon = { Icon(Icons.Default.Home, "Home") },
                    label = { Text("Home") })
                Button(onClick = {}) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(Icons.Default.Shuffle, "Random")
                        Text("Random")
                    }
                }
                NavigationBarItem(
                    selected = navBackStackEntry?.destination?.hasRoute<Favourite>() == true,
                    onClick = {
                        navController.navigate(Favourite) {
                            launchSingleTop = true
                            popUpTo<Home> {
                                inclusive = false
                                saveState = true
                            }
                            restoreState = true
                        }
                    },
                    icon = { Icon(Icons.Default.Star, "Favourite") },
                    label = { Text("Favourite") })
            }
        }
    }) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Home, Modifier.padding(innerPadding)
        ) {
            composable<Home> { HomeScreen(onOpenTrackCard = { navController.navigate(TrackCard) }) }
            composable<TrackCard> { TrackcardScreen() }
            composable<Favourite> { FavouriteScreen() }
        }
    }
}