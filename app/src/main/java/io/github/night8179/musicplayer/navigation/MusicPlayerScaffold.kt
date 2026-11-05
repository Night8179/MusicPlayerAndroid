package io.github.night8179.musicplayer.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import io.github.night8179.feature.HomeScreenWrapper
import io.github.night8179.feature.home.HomeEvent
import io.github.night8179.feature.home.HomeViewModel
import io.github.night8179.feature.ui.FavouriteScreen
import io.github.night8179.feature.ui.TrackcardScreen
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun MusicPlayerScaffold() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    Scaffold(
        bottomBar = {
            if (navBackStackEntry?.destination?.hasRoute<Screen.TrackCard>() == false) {
                NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
                    listOf(
                        BottomBarItem.Home,
                        BottomBarItem.Random,
                        BottomBarItem.Favourite
                    ).forEach { item ->
                        val selected = when (item) {
                            BottomBarItem.Home ->
                                navBackStackEntry?.destination?.hasRoute<Screen.Home>() == true

                            BottomBarItem.Favourite ->
                                navBackStackEntry?.destination?.hasRoute<Screen.Favourite>() == true

                            BottomBarItem.Random -> false
                        }
                        NavigationBarItem(
                            selected = selected,
                            icon = {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.label
                                )
                            },
                            label = { Text(text = item.label) },
                            onClick = {
                                when (item) {
                                    BottomBarItem.Home -> navController.navigate(Screen.Home) {
                                        launchSingleTop = true
                                        popUpTo<Screen.Home> {
                                            inclusive = false
                                            saveState = true
                                        }
                                        restoreState = true
                                    }

                                    BottomBarItem.Favourite -> navController.navigate(Screen.Favourite) {
                                        launchSingleTop = true
                                        popUpTo<Screen.Home> {
                                            inclusive = false
                                            saveState = true
                                        }
                                        restoreState = true
                                    }

                                    BottomBarItem.Random -> Unit
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home,
            modifier = Modifier.padding(innerPadding)
        ) {
            listOf(
                Screen.Home,
                Screen.TrackCard,
                Screen.Favourite
            ).forEach { screen ->
                when (screen) {
                    Screen.Home ->
                        composable<Screen.Home> {
                            val viewModel = koinViewModel<HomeViewModel>()

                            LaunchedEffect(viewModel) {
                                viewModel.events.collect { event ->
                                    when (event) {
                                        HomeEvent.OpenTrackCard ->
                                            navController.navigate(Screen.TrackCard)
                                    }
                                }
                            }

                            HomeScreenWrapper()
                        }

                    Screen.TrackCard ->
                        composable<Screen.TrackCard> { TrackcardScreen() }

                    Screen.Favourite ->
                        composable<Screen.Favourite> { FavouriteScreen() }
                }
            }
        }
    }
}
