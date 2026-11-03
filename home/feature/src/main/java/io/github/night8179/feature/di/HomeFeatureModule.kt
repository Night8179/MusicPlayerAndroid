package io.github.night8179.feature.di

import io.github.night8179.feature.screen.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val homeFeatureModule = module {
    viewModel{
        HomeViewModel(get())
    }
}