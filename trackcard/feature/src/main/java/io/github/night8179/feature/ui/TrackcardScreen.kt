package io.github.night8179.feature.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import io.github.night8179.feature.TrackCardInteractions
import io.github.night8179.feature.model.PlaybackState
import io.github.night8179.trackcard.feature.R

@Composable
internal fun TrackcardScreen(
    state: PlaybackState,
    interactions: TrackCardInteractions
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .padding(top = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = state.artworkUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(256.dp)
        )
        Spacer(Modifier.height(28.dp))
        TrackcardSlider(
            state = state,
            onSeek = interactions::onSeek
        )
        Spacer(Modifier.height(22.dp))
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = state.title
            )
            Text(
                text = state.artist,
                fontSize = 12.sp
            )
            if (state.hasError) {
                Text(
                    text = stringResource(R.string.trackcard_playback_error)
                )
            }
        }
        Spacer(Modifier.height(20.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            IconButton(
                onClick = {
                    interactions.onPreviousClick()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.SkipPrevious,
                    contentDescription = stringResource(R.string.trackcard_previous_track)
                )
            }
            IconButton(
                onClick = {
                    interactions.onPlayPauseClick()
                },
                enabled = !state.isBuffering
            ) {
                if (state.isBuffering) {
                    CircularProgressIndicator()
                } else {
                    Icon(
                        imageVector = if (state.isPlaying) {
                            Icons.Default.Pause
                        } else {
                            Icons.Default.PlayArrow
                        },
                        contentDescription = if (state.isPlaying) {
                            stringResource(R.string.trackcard_pause)
                        } else {
                            stringResource(R.string.trackcard_resume)
                        }
                    )
                }
                IconButton(
                    onClick = {
                        interactions.onNextClick()
                    }) {
                    Icon(
                        imageVector = Icons.Default.SkipNext,
                        contentDescription = stringResource(R.string.trackcard_next_track)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
private fun previewTrackCardScreen() {
    TrackcardScreen(
        state = PlaybackState(
            trackId = "preview",
            title = "Название трека",
            artist = "Исполнитель",
            artworkUrl = null
        ),
        interactions = TrackCardInteractions.mock()
    )
}
