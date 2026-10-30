package io.github.night8179.model.data.repository

import io.github.night8179.model.data.mapper.TrackMapper
import io.github.night8179.model.domain.model.Track
import io.github.night8179.model.domain.repository.TrackRepository
import io.github.night8179.model.domain.service.JamendoService

internal class TrackRepositoryImpl(private val service: JamendoService, private val mapper: TrackMapper) : TrackRepository {
    override suspend fun getTracks(): List<Track> {
        val response = service.getTracks()
        val tracksDTO = response.results
        val tracks: List<Track> = tracksDTO.map { dto -> mapper.fromDTO(dto) }
        return tracks
    }

    override suspend fun searchTrack(query: String): List<Track> {
        val response = service.searchTrack(query)
        val tracksDTO = response.results
        val tracks: List<Track> = tracksDTO.map { dto -> mapper.fromDTO(dto) }
        return tracks
    }
}