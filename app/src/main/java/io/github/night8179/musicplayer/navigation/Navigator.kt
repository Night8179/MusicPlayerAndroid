package io.github.night8179.musicplayer.navigation

import androidx.navigation.NavController
import io.github.night8179.feature.HomeNavigator

internal class Navigator() : HomeNavigator {
    private var navController: NavController? = null

    fun attach(controller: NavController) {
        navController = controller
    }

    fun detach(controller: NavController) {
        if (navController === controller) {
            navController = null
        }
    }

    override fun openTrackCard() {
        navController?.navigate(Route.TrackCard) {
            launchSingleTop = true
        }
    }
}
