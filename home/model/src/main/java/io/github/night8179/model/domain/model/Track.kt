package io.github.night8179.model.domain.model

public class Track(
    val id: String,
    val name: String,
    val duration: String,
    val artist_name: String,
    val image: String
) {
    override fun toString(): String {
        return "$name by $artist_name with duration: $duration"
    }
}