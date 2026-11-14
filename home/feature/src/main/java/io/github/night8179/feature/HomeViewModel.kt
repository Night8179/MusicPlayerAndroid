package io.github.night8179.feature

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.night8179.feature.mapper.mapHomeUiState
import io.github.night8179.feature.model.HomeUiState
import io.github.night8179.model.domain.repository.TrackRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

internal class HomeViewModel(
    private val trackRepository: TrackRepository,
    private val navigator: HomeNavigator
) : ViewModel(), HomeInteractions {
    private val isLoading = MutableStateFlow(false)

    val uiState = combine(
        trackRepository.tracksState,
        isLoading
    ) { tracksState, loading ->
        mapHomeUiState(tracksState, loading)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = HomeUiState.Idle
    )
    private var requestJob: Job? = null

    val searchFieldState = TextFieldState()

    init {
        loadTracks()
        observeSearchField()
    }

    override fun onTrackClick() {
        navigator.openTrackCard()
    }

    private fun scheduleSearch(
        query: String,
        delayMs: Long
    ) {
        val searchQuery = query.trim()
        requestJob?.cancel()
        requestJob = viewModelScope.launch {
            try {
                isLoading.value = false
                delay(delayMs)
                isLoading.value = true
                trackRepository.searchTrack(searchQuery)
            } finally {
                if (isActive)
                    isLoading.value = false
            }
        }
    }

    fun observeSearchField() {
        viewModelScope.launch {
            snapshotFlow {
                searchFieldState.text.toString().trim()
            }
                .drop(1)
                .collect { query ->
                    if (query.isEmpty()) {
                        requestJob?.cancel()
                        isLoading.value = false
                    } else {
                        scheduleSearch(query, delayMs = 350)
                    }
                }
        }
    }

    private fun loadTracks() {
        requestJob?.cancel()
        requestJob = viewModelScope.launch {
            isLoading.value = true
            try {
                trackRepository.getTracks()
            } finally {
                if (isActive)
                    isLoading.value = false
            }
        }
    }

    override fun searchTrack(query: String) {
        scheduleSearch(query, 0)
    }
}
