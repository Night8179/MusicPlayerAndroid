package io.github.night8179.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class TrackResponseDTO(val headers: HeadersDTO, val results: List<TrackDTO>)
