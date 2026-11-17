package io.github.night8179.feature

internal interface HomeInteractions {
    fun onTrackClick(trackId: String)
    fun searchTrack(query: String)

    companion object {
        fun mock(): HomeInteractions = object : HomeInteractions {
            override fun onTrackClick(trackId: String) = Unit
            override fun searchTrack(query: String) = Unit
        }
    }
}
