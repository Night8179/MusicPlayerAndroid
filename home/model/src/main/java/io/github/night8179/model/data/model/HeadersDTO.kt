package io.github.night8179.model.data.model

import kotlinx.serialization.Serializable

@Serializable
internal class HeadersDTO(
    val status: String,
    val code: Int,
    var error_message: String,
    val results_count: Int
)
