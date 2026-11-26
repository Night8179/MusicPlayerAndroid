package io.github.night8179.feature.player

import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import io.github.night8179.model.domain.model.PlayerTrack
import androidx.core.net.toUri
import io.github.night8179.feature.model.PlaybackState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class ExoPlayerController(
    private val player: ExoPlayer
) : PlayerController {

    private val _playbackState = MutableStateFlow(
        PlaybackState(
            trackId = null,
            title = "",
            artist = "",
            artworkUrl = null
        )
    )
    override val playbackState: StateFlow<PlaybackState> = _playbackState.asStateFlow()

    private val listener = object : Player.Listener {
        override fun onEvents(player: Player, events: Player.Events) {
            val item = player.currentMediaItem

            _playbackState.value = PlaybackState(
                trackId = item?.mediaId,
                title = item?.mediaMetadata?.title?.toString().orEmpty(),
                artist = item?.mediaMetadata?.artist?.toString().orEmpty(),
                artworkUrl = item?.mediaMetadata?.artworkUri?.toString(),
                isPlaying = player.isPlaying,
                positionMs = player.currentPosition.coerceAtLeast(0L),
                durationMs = player.duration.coerceAtLeast(0L),
                isBuffering = player.playbackState == Player.STATE_BUFFERING,
                hasError = player.playerError != null
            )
        }
    }

    init {
        player.addListener(listener)
    }

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
        if (player.playerError != null)
            player.prepare()
        if (player.playbackState == Player.STATE_ENDED)
            player.seekTo(0L)

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
        player.removeListener(listener)
        player.release()
    }

    override fun refreshProgress() {
        val current = _playbackState.value
        _playbackState.value = current.copy(
            positionMs = player.currentPosition.coerceAtLeast(0L),
            durationMs = player.duration.coerceAtLeast(0L)
        )
    }
}
