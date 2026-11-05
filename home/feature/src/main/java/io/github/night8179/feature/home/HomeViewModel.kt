package io.github.night8179.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

sealed interface HomeEvent {
    data object OpenTrackCard : HomeEvent
}

class HomeViewModel : ViewModel(), HomeInteractions {
    private val eventsChannel = Channel<HomeEvent>(Channel.BUFFERED)
    val events = eventsChannel.receiveAsFlow()

    override fun onTrackClick() {
        viewModelScope.launch {
            eventsChannel.send(HomeEvent.OpenTrackCard)
        }
    }
}
