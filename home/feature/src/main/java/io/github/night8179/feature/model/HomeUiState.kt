package io.github.night8179.feature.model

import io.github.night8179.feature.mapper.HomeErrorReason

sealed interface HomeUiState {
    data class Content(
        val tracks: List<TrackUiState>
    ) : HomeUiState

    data class Error(
        val reason: HomeErrorReason
    ) : HomeUiState

    data object Loading : HomeUiState
    data object Idle : HomeUiState
}
