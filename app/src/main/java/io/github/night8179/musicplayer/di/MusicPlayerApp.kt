package io.github.night8179.musicplayer.di

import android.app.Application
import io.github.night8179.feature.HomeNavigator
import io.github.night8179.feature.HomePlayback
import io.github.night8179.feature.di.homeFeatureModule
import io.github.night8179.feature.di.trackCardFeatureModule
import io.github.night8179.model.di.homeDataModule
import io.github.night8179.musicplayer.PlaybackBridge
import io.github.night8179.musicplayer.navigation.Navigator
import io.github.night8179.network.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.bind
import org.koin.dsl.module

class MusicPlayerApp : Application() {
    private val navigationModule = module {
        single {
            Navigator()
        } bind HomeNavigator::class

        single{
            PlaybackBridge()
        } bind HomePlayback::class
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MusicPlayerApp)
            modules(
                listOf(
                    navigationModule,
                    networkModule,
                    homeDataModule,
                    homeFeatureModule,
                    trackCardFeatureModule
                )
            )
        }
    }
}
