package io.github.night8179.model.data.model

import kotlinx.serialization.Serializable

@Serializable
internal data class TrackResponseDTO(
    val headers: HeadersDTO,
    val results: List<TrackDTO>
)
