package io.github.night8179.feature

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.night8179.feature.ui.TrackcardScreen

@Composable
public fun TrackCardWrapper(
    viewModel: PlaybackViewModel
) {
    val state by viewModel.playbackState.collectAsStateWithLifecycle()
    TrackcardScreen(
        state = state,
        interactions = viewModel
    )
}
