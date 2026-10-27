package io.github.night8179.data.mapper

import io.github.night8179.data.remote.dto.TrackDTO
import io.github.night8179.domain.model.Track

class TrackMapper {
    companion object{
        fun fromDTO(dto: TrackDTO): Track {
            return Track(
                name = dto.name,
                duration = dto.duration,
                artist_name = dto.artist_name,
                audio = dto.audio,
                image = dto.image)
        }
    }
}