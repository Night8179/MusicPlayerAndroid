package io.github.night8179.feature.state

import io.github.night8179.feature.mapper.HomeErrorReason
import io.github.night8179.feature.model.TrackUiState

sealed interface HomeUiState {
    data class Content(
        val tracks: List<TrackUiState>
    ): HomeUiState
    data class Error(
        val reason: HomeErrorReason
    ): HomeUiState
    data object Loading: HomeUiState
}
