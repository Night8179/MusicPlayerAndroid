package io.github.night8179.feature.player

import androidx.lifecycle.ViewModel

public class PlaybackViewModel(
    private val controller: PlayerController
) : ViewModel(),
    PlayerController by controller {
    override fun onCleared() {
        controller.release()
        super.onCleared()
    }
}
