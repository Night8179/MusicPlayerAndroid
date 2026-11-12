package io.github.night8179.feature

import io.github.night8179.feature.model.TrackUiState

public interface HomePlayback {
    fun selectTrack(tracks: List<TrackUiState>, index: Int)
}
