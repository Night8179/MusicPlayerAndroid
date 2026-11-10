package io.github.night8179.feature

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.night8179.feature.mapper.mapHomeUiState
import io.github.night8179.feature.mapper.toUi
import io.github.night8179.feature.model.TrackUiState
import io.github.night8179.feature.state.HomeUiState
import io.github.night8179.model.domain.model.Track
import io.github.night8179.model.domain.repository.TrackRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

internal class HomeViewModel(
    private val trackRepository: TrackRepository,
    private val navigator: HomeNavigator
) : ViewModel(), HomeInteractions {
    private val trackResult = MutableStateFlow<Result<List<Track>>?>(null)

    private val isLoading = MutableStateFlow(false)

    val uiState = combine(trackResult, isLoading) { result, loading ->
        mapHomeUiState(result, loading)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = HomeUiState.Loading
    )
    private var requestJob: Job? = null

    val searchFieldState = TextFieldState()

    init {
        observeSearchField()
    }

    override fun onTrackClick() {
        navigator.openTrackCard()
    }

    private fun scheduleSearch(
        query: String,
        delayMs: Long
    ){
        val searchQuery = query.trim()
        if (searchQuery.isEmpty()) {
            loadTracks()
            return
        }
        requestJob?.cancel()
        requestJob = viewModelScope.launch {
            try {
                delay(delayMs)
                isLoading.value = true
                val tracks: List<Track> = trackRepository.searchTrack(searchQuery)
                trackResult.value = Result.success(tracks)
            } catch (err: CancellationException) {
                throw err
            } catch (err: Exception) {
                trackResult.value = Result.failure(err)
            }
            isLoading.value = false
        }
    }

    fun observeSearchField(){
            viewModelScope.launch {
            snapshotFlow {
                val query = searchFieldState.text.toString().trim()
                if(query.length > 0) query else ""
            }.collect { query ->
                scheduleSearch(query, 350)
            }
        }
    }

    private fun loadTracks() {
        requestJob?.cancel()
        requestJob = viewModelScope.launch {
            try {
                isLoading.value = true
                val tracks: List<Track> = trackRepository.getTracks()
                trackResult.value = Result.success(tracks)
            } catch (err: CancellationException) {
                throw err
            } catch (err: Exception) {
                trackResult.value = Result.failure(err)
            }
            isLoading.value = false
        }
    }

    override fun searchTrack(query: String) {
       scheduleSearch(query, 0)
    }
}
