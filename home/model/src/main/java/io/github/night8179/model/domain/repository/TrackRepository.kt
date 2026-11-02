package io.github.night8179.model.domain.repository

import io.github.night8179.model.domain.model.Track

public interface TrackRepository {
    suspend fun getTracks(): List<Track>
    suspend fun searchTrack(query: String): List<Track>
}