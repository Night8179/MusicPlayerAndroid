package io.github.night8179.feature.state

data class HomeUiState(
    val query: String = "",
    val content: HomeContentState = HomeContentState.Loading
)
