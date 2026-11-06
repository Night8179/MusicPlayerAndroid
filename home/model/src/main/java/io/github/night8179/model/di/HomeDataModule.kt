package io.github.night8179.model.di

import io.github.night8179.model.data.repository.TrackRepositoryImpl
import io.github.night8179.model.data.service.JamendoServiceImpl
import io.github.night8179.model.domain.repository.TrackRepository
import io.github.night8179.model.domain.service.JamendoService
import org.koin.dsl.module

public val homeDataModule = module{
    single<JamendoService> {
        JamendoServiceImpl(get())
    }
    single<TrackRepository>{
        TrackRepositoryImpl(get())
    }
}