package io.github.night8179.feature.mapper

import io.github.night8179.feature.model.TrackUiState
import io.github.night8179.model.domain.model.Track

internal fun Track.toUi(): TrackUiState {
    return TrackUiState(
        id = this.id,
        title = this.name,
        artist = this.artist_name,
        durationText = this.duration,
        imageUrl = this.image
    )
}
