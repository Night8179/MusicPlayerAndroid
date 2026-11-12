package io.github.night8179.musicplayer

import io.github.night8179.feature.HomePlayback
import io.github.night8179.feature.model.TrackUiState
import io.github.night8179.feature.player.PlaybackViewModel
import io.github.night8179.musicplayer.mapper.toPlayer

class PlaybackBridge(): HomePlayback {
    private var playbackViewModel: PlaybackViewModel? = null

    fun attach(playback: PlaybackViewModel) {
        playbackViewModel = playback
    }

    fun detach(playback: PlaybackViewModel) {
        if (playbackViewModel === playback) {
            playbackViewModel = null
        }
    }

    override fun selectTrack(
        tracks: List<TrackUiState>,
        index: Int
    ) {
        if(playbackViewModel?.toggleIfCurr(trackId = tracks[index].id) == true)
            return
        val playerTrack = tracks.map { it.toPlayer() }
        playbackViewModel?.startTrack(tracks = playerTrack, index = index)
    }
}
