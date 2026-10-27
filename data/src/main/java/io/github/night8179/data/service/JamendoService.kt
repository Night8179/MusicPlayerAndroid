package io.github.night8179.data.service

import io.github.night8179.data.remote.KtorClient
import io.github.night8179.data.remote.dto.TrackResponseDTO
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

//1448099e

class JamendoService {
    companion object{
        suspend fun getTracks(): TrackResponseDTO{
            val response: TrackResponseDTO = KtorClient.netClient.get("https://api.jamendo.com/v3.0/tracks/?client_id=1448099e&format=jsonpretty").body()
            return response
        }

        suspend fun searchTrack(query: String): TrackResponseDTO{
            val response: TrackResponseDTO = KtorClient.netClient.get("https://api.jamendo.com/v3.0/tracks/"){
                parameter("client_id", "1448099e")
                parameter("format", "jsonpretty")
                parameter("namesearch", query)
            }.body()
            return response
        }
    }
}