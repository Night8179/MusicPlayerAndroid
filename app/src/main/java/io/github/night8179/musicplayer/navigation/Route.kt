package io.github.night8179.musicplayer.navigation

import kotlinx.serialization.Serializable

@Serializable
internal sealed class Route {

    @Serializable
    data object Home : Route()

    @Serializable
    data object TrackCard : Route()

    @Serializable
    data object Favourite : Route()
}
