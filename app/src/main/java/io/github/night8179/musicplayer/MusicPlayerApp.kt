package io.github.night8179.musicplayer

import android.app.Application
import io.github.night8179.model.di.homeDataModule
import io.github.night8179.network.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import kotlin.collections.listOf

class MusicPlayerApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@MusicPlayerApp)
            modules(listOf(
                networkModule,
                homeDataModule
            ))
        }
    }
}
