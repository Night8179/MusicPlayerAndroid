package io.github.night8179.feature.di

import io.github.night8179.feature.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

public val homeFeatureModule = module {
    viewModel {
        HomeViewModel(
            rep = get(),
            navigator = get()
        )
    }
}
