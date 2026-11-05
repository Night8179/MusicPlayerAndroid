package io.github.night8179.musicplayer.di

import android.app.Application
import io.github.night8179.feature.di.homeFeatureModule
import io.github.night8179.model.di.homeDataModule
import io.github.night8179.network.di.networkModule
import org.koin.core.context.startKoin

class MusicPlayerApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                listOf(
                    networkModule,
                    homeDataModule,
                    homeFeatureModule
                )
            )
        }
    }
}
