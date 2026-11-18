package io.github.night8179.feature.player

import android.net.Uri
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import io.github.night8179.model.domain.model.PlayerTrack
import androidx.core.net.toUri

internal class ExoPlayerController(
    private val player: ExoPlayer
) : PlayerController {

    override fun startTrack(
        tracks: List<PlayerTrack>,
        index: Int
    ) {
        if (tracks.isNotEmpty()) {
            if (index !in tracks.indices)
                return
            val mediaItemList: List<MediaItem> = tracks.map { track ->
                val metadata = MediaMetadata.Builder()
                    .setTitle(track.name)
                    .setArtist(track.artist_name)
                    .setArtworkUri(track.image.toUri())
                    .build()
                MediaItem.Builder()
                    .setMediaId(track.id)
                    .setUri(track.audioUrl)
                    .setMediaMetadata(metadata)
                    .build()
            }
            player.setMediaItems(
                mediaItemList,
                index,
                0L
            )
            player.prepare()
            player.play()
        }
    }

    override fun resume() {
        player.play()
    }

    override fun toggleIfCurr(trackId: String): Boolean {
        if (player.playbackState == Player.STATE_ENDED)
            return false

        if (player.currentMediaItem?.mediaId == trackId) {
            if (player.playWhenReady) {
                pause()
                return true
            } else {
                resume()
                return true
            }
        } else return false
    }

    override fun pause() {
        player.pause()
    }

    override fun seekTo(positionMs: Long) {
        player.seekTo(positionMs)
    }

    override fun nextTrack() {
        if (player.hasNextMediaItem())
            player.seekToNextMediaItem()
    }

    override fun previousTrack() {
        if (player.hasPreviousMediaItem())
            player.seekToPreviousMediaItem()
    }

    override fun release() {
        player.release()
    }
}
