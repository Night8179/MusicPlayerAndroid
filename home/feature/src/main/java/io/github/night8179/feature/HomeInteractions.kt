package io.github.night8179.feature

internal interface HomeInteractions {
    fun onTrackClick()
    fun searchTrack(query: String)

    companion object {
        fun mock(): HomeInteractions = object : HomeInteractions {
            override fun onTrackClick() = Unit
            override fun searchTrack(query: String) = Unit
        }
    }
}
