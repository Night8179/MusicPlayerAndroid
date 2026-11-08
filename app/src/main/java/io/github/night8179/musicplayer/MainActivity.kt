package io.github.night8179.musicplayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import io.github.night8179.feature.player.PlaybackViewModel
import io.github.night8179.musicplayer.navigation.Menu
import io.github.night8179.musicplayer.ui.theme.MusicPlayerTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val playbackViewModel: PlaybackViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MusicPlayerTheme {
                Menu(playbackViewModel)
            }
        }
    }

    override fun onStop() {
        playbackViewModel.pause()
        super.onStop()
    }
}
