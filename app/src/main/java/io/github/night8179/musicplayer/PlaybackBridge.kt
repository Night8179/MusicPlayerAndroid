package io.github.night8179.musicplayer

import io.github.night8179.feature.HomePlayback
import io.github.night8179.feature.model.TrackUiState
import io.github.night8179.feature.PlaybackViewModel
import io.github.night8179.musicplayer.mapper.toPlayer

class PlaybackBridge() : HomePlayback {
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
        val playback = playbackViewModel ?: return
        val selectedTrack = tracks.getOrNull(index)?.id ?: return

        if (playback.toggleIfCurr(trackId = selectedTrack)) return

        val playerTrack = tracks.map { it.toPlayer() }
        playback.startTrack(tracks = playerTrack, index = index)
    }
}
