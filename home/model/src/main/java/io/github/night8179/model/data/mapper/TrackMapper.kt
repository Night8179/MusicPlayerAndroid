package io.github.night8179.model.data.mapper

import io.github.night8179.model.data.model.TrackDTO
import io.github.night8179.model.domain.model.Track

internal class TrackMapper {
    fun fromDTO(dto: TrackDTO): Track {
        return Track(
            id = dto.id,
            name = dto.name,
            duration = run {
                val hour = dto.duration / 3600
                val min = (dto.duration % 3600) / 60
                val sec = dto.duration % 60
                val str: String
                if(hour > 0)
                    str = "%d:%02d:%02d".format(hour, min, sec)
                else
                    str = "%02d:%02d".format(min, sec)
                str
            },
            artist_name = dto.artist_name,
            image = dto.image
        )
    }
}