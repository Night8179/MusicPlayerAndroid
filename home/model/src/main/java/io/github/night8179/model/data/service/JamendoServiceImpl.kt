package io.github.night8179.model.data.service

import io.github.night8179.model.data.model.TrackResponseDTO
import io.github.night8179.model.domain.service.JamendoService
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

internal class JamendoServiceImpl(private val client: HttpClient) : JamendoService {
    override suspend fun getTracks(): TrackResponseDTO {
        val response: TrackResponseDTO =
            client.get("https://api.jamendo.com/v3.0/tracks/") {
                parameter("client_id", "1448099e")
                parameter("format", "jsonpretty")
            }.body()
        return response
    }

    override suspend fun searchTrack(query: String): TrackResponseDTO {
        val response: TrackResponseDTO =
            client.get("https://api.jamendo.com/v3.0/tracks/") {
                parameter("client_id", "1448099e")
                parameter("format", "jsonpretty")
                parameter("search", query)
            }.body()
        return response
    }
}
