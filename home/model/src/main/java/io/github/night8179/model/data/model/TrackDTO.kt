package io.github.night8179.model.data.model

import kotlinx.serialization.Serializable

@Serializable
internal data class TrackDTO(
    val name: String,
    val duration: Int,
    val artist_name: String,
    val image: String
)