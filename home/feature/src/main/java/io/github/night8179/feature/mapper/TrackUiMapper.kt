package io.github.night8179.feature.mapper

import io.github.night8179.feature.model.TrackUi
import io.github.night8179.model.domain.model.Track

class TrackUiMapper {
    fun toUi(track: Track): TrackUi{
        return TrackUi(
            id = track.id,
            title = track.name,
            artist = track.artist_name,
            durationText = track.duration,
            imageUrl = track.image
        )
    }
}