package io.github.night8179.feature.di

import io.github.night8179.feature.HomeViewModel
import io.github.night8179.feature.mapper.HomeUiStateMapper
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

public val homeFeatureModule = module {
    viewModel {
        HomeViewModel(
            trackRepository = get(),
            navigator = get(),
            uiStateMapper = get()
        )
    }

    single {
        HomeUiStateMapper()
    }
}
