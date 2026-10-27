package io.github.night8179.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class HeadersDTO(val status: String,
                      val code: Int,
                      var error_message: String,
                      val result_count: String,
                      val next: String)
