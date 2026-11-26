package io.github.night8179.feature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.night8179.feature.player.PlayerController
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

public class PlaybackViewModel(
    private val controller: PlayerController
) : ViewModel(), TrackCardInteractions, PlayerController by controller {

    init {
        viewModelScope.launch {
            while (isActive) {
                controller.refreshProgress()
                delay(500L)
            }
        }
    }

    override fun onCleared() {
        controller.release()
        super.onCleared()
    }

    override fun onPlayPauseClick() {
        if (controller.playbackState.value.isPlaying)
            controller.pause()
        else
            controller.resume()
    }

    override fun onNextClick() {
        controller.nextTrack()
    }

    override fun onPreviousClick() {
        controller.previousTrack()
    }

    override fun onSeek(positionMs: Long) {
        controller.seekTo(positionMs)
        controller.refreshProgress()
    }
}
