package io.github.night8179.model.di

import io.github.night8179.model.data.mapper.TrackMapper
import io.github.night8179.model.data.repository.TrackRepositoryImpl
import io.github.night8179.model.data.service.JamendoServiceImpl
import io.github.night8179.model.domain.repository.TrackRepository
import io.github.night8179.model.domain.service.JamendoService
import org.koin.dsl.module

val homeDataModule = module{
    single<JamendoService> {
        JamendoServiceImpl(get())
    }
    single{
        TrackMapper()
    }
    single<TrackRepository>{
        TrackRepositoryImpl(get(), get())
    }
}