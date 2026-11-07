package io.github.night8179.model.data.mapper

import io.github.night8179.model.data.model.TrackDTO
import io.github.night8179.model.domain.model.Track

internal fun TrackDTO.toDomain(): Track {
    return Track(
        id = this.id,
        name = this.name,
        duration = run {
            val hour = this.duration / 3600
            val min = (this.duration % 3600) / 60
            val sec = this.duration % 60
            val str: String
            if (hour > 0)
                str = "%d:%02d:%02d".format(hour, min, sec)
            else
                str = "%02d:%02d".format(min, sec)
            str
        },
        artist_name = this.artist_name,
        image = this.image
    )
}
