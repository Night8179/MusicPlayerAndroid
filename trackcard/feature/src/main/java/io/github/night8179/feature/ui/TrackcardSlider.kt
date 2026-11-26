package io.github.night8179.feature.ui

import android.text.format.DateUtils
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import io.github.night8179.feature.model.PlaybackState

@Composable
fun TrackcardSlider(
    state: PlaybackState,
    onSeek: (Long) -> Unit
) {
    val progress = if (state.durationMs > 0) {
        (state.positionMs.toFloat() / state.durationMs).coerceIn(0f, 1f)
    } else {
        0f
    }

    var draggedProgress by remember(state.trackId) {
        mutableStateOf<Float?>(null)
    }

    val selectedProgress = draggedProgress
    val displayedPositionMs = if (selectedProgress != null) {
        (selectedProgress * state.durationMs).toLong()
    } else {
        state.positionMs
    }

    Slider(
        value = draggedProgress ?: progress,
        onValueChange = { newProgress ->
            draggedProgress = newProgress
        },
        onValueChangeFinished = {
            val selectedProgress = draggedProgress
            if (selectedProgress != null) {
                val positionMs = (selectedProgress * state.durationMs).toLong()
                onSeek(positionMs)
            }
            draggedProgress = null
        },
        enabled = state.durationMs > 0,
        modifier = Modifier.fillMaxWidth()
    )
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = DateUtils.formatElapsedTime(displayedPositionMs / 1000L)
        )
        Text(
            text = DateUtils.formatElapsedTime(state.durationMs / 1000L)
        )
    }
}
