package io.github.night8179.feature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.night8179.feature.mapper.toUi
import io.github.night8179.feature.model.TrackUi
import io.github.night8179.feature.state.HomeContentState
import io.github.night8179.feature.state.HomeUiState
import io.github.night8179.model.domain.model.Track
import io.github.night8179.model.domain.repository.TrackRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class HomeViewModel(
    private val rep: TrackRepository,
    private val navigator: HomeNavigator
) : ViewModel(), HomeInteractions {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private var requestJob: Job? = null


    init {
        loadTracks()
    }

    override fun onTrackClick() {
        navigator.openTrackCard()
    }

    private fun loadTracks() {
        requestJob?.cancel()
        requestJob = viewModelScope.launch {
            try {
                _uiState.update { curr ->
                    curr.copy(
                        content = HomeContentState.Loading
                    )
                }
                val tracks: List<Track> = rep.getTracks()
                val tracksUi: List<TrackUi> = tracks.map { it.toUi() }
                if (tracksUi.isNotEmpty()) {
                    _uiState.update { curr ->
                        curr.copy(
                            content = HomeContentState.Content(tracksUi)
                        )
                    }
                } else {
                    _uiState.update { curr ->
                        curr.copy(
                            content = HomeContentState.Empty
                        )
                    }
                }
            } catch (err: CancellationException) {
                throw err
            } catch (err: Exception) {
                _uiState.update { curr ->
                    curr.copy(
                        content = HomeContentState.Error("Не удалось загрузить треки. ${err.message}")
                    )
                }
            }
        }
    }

    override fun updateQuery(newQuery: String) {
        _uiState.update { curr ->
            curr.copy(
                query = newQuery
            )
        }
    }

    override fun searchTrack(query: String) {
        val searchQuery = query.trim()
        if (searchQuery.isEmpty()) {
            loadTracks()
            return
        }
        requestJob?.cancel()
        requestJob = viewModelScope.launch {
            try {
                _uiState.update { curr ->
                    curr.copy(
                        content = HomeContentState.Loading
                    )
                }
                val tracks: List<Track> = rep.searchTrack(searchQuery)
                val tracksUi: List<TrackUi> = tracks.map { it.toUi() }
                if (tracksUi.isNotEmpty()) {
                    _uiState.update { curr ->
                        curr.copy(
                            content = HomeContentState.Content(tracksUi)
                        )
                    }
                } else {
                    _uiState.update { curr ->
                        curr.copy(
                            content = HomeContentState.Empty
                        )
                    }
                }
            } catch (err: CancellationException) {
                throw err
            } catch (err: Exception) {
                _uiState.update { curr ->
                    curr.copy(
                        content = HomeContentState.Error("Не удалось загрузить треки. ${err.message}")
                    )
                }
            }
        }
    }
}
