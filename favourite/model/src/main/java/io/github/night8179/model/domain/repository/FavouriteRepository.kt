package io.github.night8179.model.domain.repository

import io.github.night8179.model.domain.model.FavouriteTrack

internal interface FavouriteRepository {
    suspend fun getTracks(): List<FavouriteTrack>
    suspend fun searchTracks(
        query: String
    ): List<FavouriteTrack>
    suspend fun addTrack(
        track: FavouriteTrack
    )
    suspend fun removeTrack(
        track: FavouriteTrack
    )
    fun isFavourite(
        track: FavouriteTrack
    ): Boolean
}
