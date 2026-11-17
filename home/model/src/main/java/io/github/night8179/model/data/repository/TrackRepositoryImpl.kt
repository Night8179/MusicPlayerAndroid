package io.github.night8179.model.data.repository

import io.github.night8179.model.data.mapper.toDomain
import io.github.night8179.model.domain.model.Track
import io.github.night8179.model.domain.model.TrackState
import io.github.night8179.model.domain.repository.TrackRepository
import io.github.night8179.model.domain.service.JamendoService
import io.ktor.utils.io.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class TrackRepositoryImpl(
    private val service: JamendoService
) : TrackRepository {
    private val _tracksState = MutableStateFlow<TrackState>(TrackState.Idle)

    override val tracksState = _tracksState.asStateFlow()

    override suspend fun getTracks() {
        try{
            val response = service.getTracks()
            val tracksDTO = response.results
            val tracks: List<Track> = tracksDTO.map { it.toDomain() }
            _tracksState.value = TrackState.Loaded(tracks)
        }
        catch(err: CancellationException){
            throw err
        }
        catch(err: Exception){
            _tracksState.value = TrackState.Failed
        }
    }

    override suspend fun searchTrack(query: String) {
        try{
            val response = service.searchTrack(query)
            val tracksDTO = response.results
            val tracks: List<Track> = tracksDTO.map { it.toDomain() }
            _tracksState.value = TrackState.Loaded(tracks)
        }
        catch(err: CancellationException){
            throw err
        }
        catch(err: Exception){
            _tracksState.value = TrackState.Failed
        }
    }
}
