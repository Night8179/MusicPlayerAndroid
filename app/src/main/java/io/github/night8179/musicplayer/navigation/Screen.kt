package io.github.night8179.musicplayer.navigation

import kotlinx.serialization.Serializable

@Serializable
internal sealed class Screen {

    @Serializable
    data object Home : Screen()

    @Serializable
    data object TrackCard : Screen()

    @Serializable
    data object Favourite : Screen()
}
