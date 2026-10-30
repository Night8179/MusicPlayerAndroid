package io.github.night8179.model.domain.repository

import io.github.night8179.model.domain.model.PlayerTrack

interface PlayerController {
    fun startTrack(tracks: List<PlayerTrack>, index: Int)
    fun resume()
    fun pause()
    fun seekTo(position: Int)
    fun nextTrack()
    fun previousTrack()
}