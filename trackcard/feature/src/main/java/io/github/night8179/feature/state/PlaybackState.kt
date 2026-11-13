package io.github.night8179.feature.state

import android.R

data class PlaybackState(
    val trackId: String?,
    val title: String,
    val artist: String,
    val artworkUrl: String?
)
