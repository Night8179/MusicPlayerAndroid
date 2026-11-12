package io.github.night8179.feature.mapper

import io.github.night8179.feature.state.HomeUiState
import io.github.night8179.model.domain.model.Track

enum class HomeErrorReason {
    RequestFailed,
    NoTracks
}

fun mapHomeUiState(
    result: Result<List<Track>>?,
    isLoading: Boolean
): HomeUiState {
    if (isLoading || result == null)
        return HomeUiState.Loading

    return result.fold(
        onSuccess = { tracks ->
            if (tracks.isNotEmpty())
                HomeUiState.Content(tracks.map { it.toUi() })
            else
                HomeUiState.Error(HomeErrorReason.NoTracks)
        },
        onFailure = {
            HomeUiState.Error(HomeErrorReason.RequestFailed)
        }
    )
}
