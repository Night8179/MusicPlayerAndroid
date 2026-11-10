package io.github.night8179.feature.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.night8179.feature.HomeInteractions
import io.github.night8179.feature.mapper.HomeErrorReason
import io.github.night8179.feature.state.HomeUiState

@Composable
internal fun HomeScreen(
    state: HomeUiState,
    interaction: HomeInteractions,
    searchFieldState: TextFieldState
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HomeSearchField(
            fieldState = searchFieldState,
            onSearch = {
                interaction.searchTrack(
                    query = searchFieldState.text.toString()
                )
            }
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            when (state) {
                is HomeUiState.Content -> LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(
                        items = state.tracks,
                        key = { track -> track.id }
                    ) { track ->
                        TrackRow(
                            track = track,
                            onClick = interaction::onTrackClick
                        )
                    }
                }

                is HomeUiState.Error -> Text(
                    text = when (state.reason) {
                        HomeErrorReason.NoTracks -> "Треки не найдены"
                        HomeErrorReason.RequestFailed -> "Не удалось загрузить треки"
                    },
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(24.dp)
                )

                HomeUiState.Loading -> CircularProgressIndicator()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun previewHomeScreen(){
    HomeScreen(
        state = HomeUiState.Loading,
        interaction = HomeInteractions.mock(),
        searchFieldState = rememberTextFieldState()
    )
}
