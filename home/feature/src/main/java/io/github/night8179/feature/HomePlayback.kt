package io.github.night8179.feature

import io.github.night8179.feature.model.TrackUi

public interface HomePlayback {
    fun selectTrack(tracks: List<TrackUi>, index: Int)
}
