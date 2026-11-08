package io.github.night8179.musicplayer.navigation

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import io.github.night8179.feature.HomeScreenWrapper
import io.github.night8179.feature.ui.FavouriteScreen
import io.github.night8179.feature.ui.TrackcardScreen
import io.github.night8179.musicplayer.R
import org.koin.compose.koinInject

@Composable
internal fun Menu() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val navigator = koinInject<Navigator>()
    DisposableEffect(
        navigator,
        navController
    ) {
        navigator.attach(navController)
        onDispose {
            navigator.detach(navController)
        }
    }

    val tabs = listOf(
        NavTab(
            route = Route.Home,
            labelRes = stringResource(R.string.tab_home),
            icon = Icons.Default.Home
        ),
        NavTab(
            route = Route.Favourite,
            labelRes = stringResource(R.string.tab_favourite),
            icon = Icons.Default.Star
        )
    )

    Scaffold(
        bottomBar = {
            if (navBackStackEntry?.destination?.hasRoute<Route.TrackCard>() == false) {
                PrimaryTabRow(
                    selectedTabIndex = if (navBackStackEntry?.destination?.hasRoute<Route.Favourite>() == true) 2 else 0,
                    modifier = Modifier.navigationBarsPadding()
                ) {
                    tabs.forEachIndexed { index, tab ->
                        if (index == 1) {
                            IconButton(
                                onClick = {
                                    Unit
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Shuffle,
                                    contentDescription = stringResource(R.string.tab_random)
                                )
                            }
                        }
                        Tab(
                            selected = navBackStackEntry?.destination?.hasRoute(tab.route::class) == true,
                            onClick = {
                                navController.navigate(tab.route) {
                                    launchSingleTop = true
                                    popUpTo<Route.Home> {
                                        saveState = true
                                    }
                                    restoreState = true
                                }
                            },
                            text = {
                                Text(
                                    text = tab.labelRes
                                )
                            },
                            icon = {
                                Icon(
                                    imageVector = tab.icon,
                                    contentDescription = null
                                )
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Route.Home,
            modifier = Modifier.padding(innerPadding)
        ) {
            listOf(
                Route.Home,
                Route.TrackCard,
                Route.Favourite
            ).forEach { route ->
                when (route) {
                    Route.Home ->
                        composable<Route.Home> {
                            HomeScreenWrapper()
                        }

                    Route.TrackCard ->
                        composable<Route.TrackCard> {
                            TrackcardScreen()
                        }

                    Route.Favourite ->
                        composable<Route.Favourite> {
                            FavouriteScreen()
                        }
                }
            }
        }
    }
}
