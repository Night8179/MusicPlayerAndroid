package io.github.night8179.feature

import androidx.compose.runtime.Composable
import io.github.night8179.feature.home.HomeViewModel
import io.github.night8179.feature.ui.HomeScreen
import org.koin.compose.viewmodel.koinViewModel

@Composable
public fun HomeScreenWrapper() {
    val viewModel = koinViewModel<HomeViewModel>()
    HomeScreen(interaction = viewModel)
}
