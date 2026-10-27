package io.github.night8179.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class KtorClient {
    companion object{
        val netClient = HttpClient(Android){
            install(ContentNegotiation){
                json(Json{
                    prettyPrint = true
                    ignoreUnknownKeys = true
                })
            }
        }
        fun close(){
            netClient.close()
        }
    }
}