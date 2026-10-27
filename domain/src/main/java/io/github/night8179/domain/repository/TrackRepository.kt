package io.github.night8179.domain.repository

import io.github.night8179.domain.model.Track

interface TrackRepository {
    suspend fun getTracks(): List<Track>
    suspend fun searchTrack(query: String): List<Track>
}