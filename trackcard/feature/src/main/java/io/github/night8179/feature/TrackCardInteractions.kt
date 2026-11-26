package io.github.night8179.feature

internal interface TrackCardInteractions {
    fun onPlayPauseClick()
    fun onNextClick()
    fun onPreviousClick()
    fun onSeek(positionMs: Long)

    companion object {
        fun mock(): TrackCardInteractions = object : TrackCardInteractions {
            override fun onPlayPauseClick() = Unit
            override fun onNextClick() = Unit
            override fun onPreviousClick() = Unit
            override fun onSeek(positionMs: Long) = Unit
        }
    }
}
