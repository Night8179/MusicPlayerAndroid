package io.github.night8179.musicplayer.mapper

import io.github.night8179.feature.model.TrackUiState
import io.github.night8179.model.domain.model.PlayerTrack

internal fun TrackUiState.toPlayer(): PlayerTrack {
    return PlayerTrack(
        id = this.id,
        name = this.title,
        artist_name = this.artist,
        duration = this.durationText,
        image = this.imageUrl,
        audioUrl = this.audioUrl
    )
}
