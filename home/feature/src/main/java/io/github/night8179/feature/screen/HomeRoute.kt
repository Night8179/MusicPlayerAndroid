package io.github.night8179.feature.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeRoute(onOpenTrackCard: () -> Unit) {
    val viewModel = koinViewModel<HomeViewModel>()
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    HomeScreen(
        state,
        onOpenTrackCard,
        onQueryChange = { text -> viewModel.updateQuery(text) },
        onSearch = { viewModel.searchTrack(state.query) })
}