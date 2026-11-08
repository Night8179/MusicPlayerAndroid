package io.github.night8179.feature.state

import io.github.night8179.feature.model.TrackUi

sealed interface HomeContentState {
    data class Content(
        val tracks: List<TrackUi>
    ): HomeContentState
    data class Error(
        val msg: String
    ): HomeContentState
    data object Loading: HomeContentState
    data object Empty: HomeContentState
}
