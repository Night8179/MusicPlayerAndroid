package io.github.night8179.model.data.repository

import io.github.night8179.model.data.mapper.toDomain
import io.github.night8179.model.domain.model.Track
import io.github.night8179.model.domain.repository.TrackRepository
import io.github.night8179.model.domain.service.JamendoService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class TrackRepositoryImpl(
    private val service: JamendoService
) : TrackRepository {
    private val _tracksState = MutableStateFlow<List<Track>?>(null)

    override val tracksState = _tracksState.asStateFlow()

    override suspend fun getTracks() {
        val response = service.getTracks()
        val tracksDTO = response.results
        val tracks: List<Track> = tracksDTO.map { it.toDomain() }
        _tracksState.value = tracks
    }

    override suspend fun searchTrack(query: String) {
        val response = service.searchTrack(query)
        val tracksDTO = response.results
        val tracks: List<Track> = tracksDTO.map { it.toDomain() }
        _tracksState.value = tracks
    }
}
