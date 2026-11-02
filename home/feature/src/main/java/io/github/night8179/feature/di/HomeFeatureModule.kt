package io.github.night8179.feature.di

import io.github.night8179.feature.mapper.TrackUiMapper
import io.github.night8179.feature.screen.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val homeFeatureModule = module {
    single{
        TrackUiMapper()
    }
    viewModel{
        HomeViewModel(get(), get())
    }
}