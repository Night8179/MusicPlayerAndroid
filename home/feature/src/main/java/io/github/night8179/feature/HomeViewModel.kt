package io.github.night8179.feature

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.night8179.feature.mapper.HomeErrorReason
import io.github.night8179.feature.mapper.HomeUiStateMapper
import io.github.night8179.feature.model.HomeUiState
import io.github.night8179.model.domain.repository.TrackRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

internal class HomeViewModel(
    private val trackRepository: TrackRepository,
    private val navigator: HomeNavigator,
    private val playback: HomePlayback,
    private val uiStateMapper: HomeUiStateMapper
) : ViewModel(), HomeInteractions {
    private val isLoading = MutableStateFlow(false)
    private val requestError = MutableStateFlow<HomeErrorReason?>(null)

    val uiState = combine(
        trackRepository.tracksState,
        isLoading,
        requestError
    ) { tracksState, loading, error ->
        uiStateMapper.map(tracksState, loading, error)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = HomeUiState.Idle
    )
    private var requestJob: Job? = null
    private var lastSearchQuery: String? = null

    val searchFieldState = TextFieldState()

    init {
        loadTracks()
        observeSearchField()
    }

    override fun onTrackClick(trackId: String) {
        val content = uiState.value
        if (content !is HomeUiState.Content) return

        val tracks = content.tracks
        val index = tracks.indexOfFirst { track ->
            track.id == trackId
        }
        if (index == -1) return
        playback.selectTrack(tracks, index)
    }

    override fun onTrackCardClick(trackId: String) {
        navigator.openTrackCard()
    }

    @OptIn(FlowPreview::class)
    private fun observeSearchField() {
        viewModelScope.launch {
            snapshotFlow {
                searchFieldState.text.toString().trim()
            }
                .drop(1)
                .debounce { query -> if (query.isEmpty()) 0L else 350L }
                .collect { query ->
                    if (query.isEmpty()) {
                        requestJob?.cancel()
                        isLoading.value = false
                        lastSearchQuery = null
                    } else if (query != lastSearchQuery) {
                        searchTrack(query)
                    }
                }
        }
    }

    private fun requestTracks(fetch: suspend () -> Unit) {
        requestJob?.cancel()
        requestJob = viewModelScope.launch {
            isLoading.value = true
            requestError.value = null
            runCatching {
                fetch()
            }.fold(
                onSuccess = {
                    isLoading.value = false
                },
                onFailure = { err ->
                    if (err is CancellationException) throw err

                    requestError.value = HomeErrorReason.RequestFailed
                    isLoading.value = false
                }
            )
        }
    }

    private fun loadTracks() {
        requestTracks {
            trackRepository.getTracks()
        }
    }

    override fun searchTrack(query: String) {
        lastSearchQuery = query.trim()
        requestTracks {
            trackRepository.searchTrack(query.trim())
        }
    }
}
