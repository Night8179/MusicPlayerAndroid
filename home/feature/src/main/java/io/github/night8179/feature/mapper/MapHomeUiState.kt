package io.github.night8179.feature.mapper

import io.github.night8179.feature.model.HomeUiState
import io.github.night8179.model.domain.model.TrackState

enum class HomeErrorReason {
    RequestFailed,
    NoTracks
}

fun mapHomeUiState(
    tracksState: TrackState,
    isLoading: Boolean
): HomeUiState {
    if (isLoading)
        return HomeUiState.Loading

    return when (tracksState) {
        TrackState.Idle -> HomeUiState.Idle
        TrackState.Failed -> HomeUiState.Error(HomeErrorReason.RequestFailed)
        is TrackState.Loaded -> {
            if (tracksState.tracks.isNotEmpty())
                HomeUiState.Content(tracksState.tracks.map { track -> track.toUi() })
            else
                HomeUiState.Error(HomeErrorReason.NoTracks)
        }
    }
}
