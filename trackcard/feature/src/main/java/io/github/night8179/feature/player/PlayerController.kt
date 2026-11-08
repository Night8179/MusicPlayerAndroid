package io.github.night8179.feature.player

import io.github.night8179.model.domain.model.PlayerTrack

public interface PlayerController {
    fun startTrack(tracks: List<PlayerTrack>, index: Int)
    fun resume()
    fun pause()
    fun toggleIfCurr(trackId: String): Boolean
    fun seekTo(positionMs: Long)
    fun nextTrack()
    fun previousTrack()
    fun release()
}
