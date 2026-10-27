package io.github.night8179.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class TrackDTO(val name: String,
                    val duration: Int,
                    val artist_name: String,
                    val audio: String,
                    val image: String)