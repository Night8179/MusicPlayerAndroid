package io.github.night8179.feature

import androidx.lifecycle.ViewModel
import io.github.night8179.feature.player.PlayerController

public class PlaybackViewModel(
    private val controller: PlayerController
) : ViewModel(), PlayerController by controller {

    override fun onCleared() {
        controller.release()
        super.onCleared()
    }
}
