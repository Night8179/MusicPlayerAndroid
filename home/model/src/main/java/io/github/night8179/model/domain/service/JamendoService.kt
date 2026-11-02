package io.github.night8179.model.domain.service

import io.github.night8179.model.data.model.TrackResponseDTO

internal interface JamendoService {
    suspend fun getTracks(): TrackResponseDTO
    suspend fun searchTrack(query: String): TrackResponseDTO
}