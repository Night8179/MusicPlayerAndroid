package io.github.night8179.feature.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.github.night8179.feature.home.HomeInteractions
import io.github.night8179.feature.state.HomeContentState
import io.github.night8179.feature.state.HomeUiState

@Composable
internal fun HomeScreen(
    state: HomeUiState,
    interaction: HomeInteractions
) {
    Column(modifier = Modifier.fillMaxSize()) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.query,
            onValueChange = interaction::updateQuery,
            label = { Text(text = "Поиск треков") },
            singleLine = true,
            trailingIcon = {
                IconButton(onClick = { interaction.searchTrack(state.query) }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Найти треки"
                    )
                }
            }
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            when (val content = state.content) {
                is HomeContentState.Content -> LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(
                        items = content.tracks,
                        key = { track -> track.id }
                    ) { track ->
                        TrackRow(
                            track = track,
                            onClick = interaction::onTrackClick
                        )
                    }
                }

                is HomeContentState.Error -> Text(
                    text = content.msg,
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(24.dp)
                )

                HomeContentState.Loading -> CircularProgressIndicator()
                HomeContentState.Empty -> Text(
                    text = "Треки не найдены",
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(24.dp)
                )
            }
        }
    }
}
