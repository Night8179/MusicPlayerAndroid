package io.github.night8179.feature.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import io.github.night8179.feature.state.HomeContentState
import io.github.night8179.feature.state.HomeUiState

@Composable
fun HomeScreen(state: HomeUiState, onQueryChange: (String) -> Unit, onSearch: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Column {
            OutlinedTextField(
                value = state.query,
                onValueChange = onQueryChange,
                label = { Text("Поиск треков") }
            )
            Button(onClick = onSearch) {
                Text("Найти")
            }
            Text("HomeScreen")
            when (val content = state.content) {
                is HomeContentState.Content -> Text("Треков: ${content.tracks.size}")
                is HomeContentState.Error -> Text(content.msg)
                HomeContentState.Loading -> Text("Загрузка")
                HomeContentState.Empty -> Text("Треки не найдены")
            }
        }
    }
}