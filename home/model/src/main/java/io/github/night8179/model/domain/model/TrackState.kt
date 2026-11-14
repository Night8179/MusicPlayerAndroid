package io.github.night8179.model.domain.model

sealed interface TrackState {
    data object Idle: TrackState
    data class Loaded(val tracks: List<Track>): TrackState
    data object Failed: TrackState
}
