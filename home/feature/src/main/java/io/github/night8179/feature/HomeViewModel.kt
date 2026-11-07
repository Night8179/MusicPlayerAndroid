package io.github.night8179.feature

import androidx.lifecycle.ViewModel

public class HomeViewModel(
    private val navigator: HomeNavigator
) : ViewModel(), HomeInteractions {

    override fun onTrackClick() {
        navigator.openTrackCard()
    }
}
