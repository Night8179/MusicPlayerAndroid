package io.github.night8179.model.domain.repository

import io.github.night8179.model.domain.model.Track
import kotlinx.coroutines.flow.StateFlow

public interface TrackRepository {
    val tracksState: StateFlow<List<Track>?>
    suspend fun getTracks()
    suspend fun searchTrack(
        query: String
    )
}
