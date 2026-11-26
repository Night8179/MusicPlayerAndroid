package io.github.night8179.feature.model

data class PlaybackState(
    val trackId: String?,
    val title: String,
    val artist: String,
    val artworkUrl: String?,
    val isPlaying: Boolean = false,
    val positionMs: Long = 0L,
    val durationMs: Long = 0L,
    val isBuffering: Boolean = false,
    val hasError: Boolean = false
)
