package io.github.night8179.model.domain.repository

import io.github.night8179.model.domain.model.TrackState
import kotlinx.coroutines.flow.StateFlow

public interface TrackRepository {
    val tracksState: StateFlow<TrackState>
    suspend fun getTracks()
    suspend fun searchTrack(
        query: String
    )
}
