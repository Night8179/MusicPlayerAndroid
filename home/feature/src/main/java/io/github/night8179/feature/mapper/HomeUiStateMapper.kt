package io.github.night8179.feature.mapper

import io.github.night8179.feature.model.HomeUiState
import io.github.night8179.model.domain.model.Track

enum class HomeErrorReason {
    RequestFailed,
    NoTracks
}

internal class HomeUiStateMapper {
    fun map(
        tracksState: List<Track>?,
        isLoading: Boolean,
        error: HomeErrorReason?
    ): HomeUiState {
        return when {
            isLoading -> HomeUiState.Loading
            error != null -> HomeUiState.Error(error)
            tracksState == null -> HomeUiState.Idle
            tracksState.isEmpty() -> HomeUiState.Error(HomeErrorReason.NoTracks)
            else -> HomeUiState.Content(tracksState.map { it.toUi() })
        }
    }
}
