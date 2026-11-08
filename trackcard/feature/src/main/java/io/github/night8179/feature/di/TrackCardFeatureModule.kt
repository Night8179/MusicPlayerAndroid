package io.github.night8179.feature.di

import androidx.media3.exoplayer.ExoPlayer
import io.github.night8179.feature.player.ExoPlayerController
import io.github.night8179.feature.player.PlaybackViewModel
import io.github.night8179.feature.player.PlayerController
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

public val trackCardFeatureModule = module {
    factory<PlayerController> {
        ExoPlayerController(
            ExoPlayer.Builder(androidContext()).build()
        )
    }
    viewModel {
        PlaybackViewModel(get())
    }
}
