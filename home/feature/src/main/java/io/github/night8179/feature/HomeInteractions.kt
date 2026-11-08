package io.github.night8179.feature

internal interface HomeInteractions {
    fun onTrackClick(trackId: String)
    fun updateQuery(newQuery: String)
    fun searchTrack(query: String)
}
