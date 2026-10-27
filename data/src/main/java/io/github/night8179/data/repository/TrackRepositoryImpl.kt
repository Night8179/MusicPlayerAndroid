package io.github.night8179.data.repository

import io.github.night8179.data.mapper.TrackMapper
import io.github.night8179.data.service.JamendoService
import io.github.night8179.domain.model.Track
import io.github.night8179.domain.repository.TrackRepository

class TrackRepositoryImpl: TrackRepository {
    override suspend fun getTracks(): List<Track> {
        val response = JamendoService.getTracks()
        val tracksDTO = response.results
        val tracks: List<Track> = tracksDTO.map { dto -> TrackMapper.fromDTO(dto) }
        return tracks
    }

    override suspend fun searchTrack(query: String): List<Track> {
        val response = JamendoService.searchTrack(query)
        val tracksDTO = response.results
        val tracks: List<Track> = tracksDTO.map { dto -> TrackMapper.fromDTO(dto) }
        return tracks
    }
}