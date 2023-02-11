package com.geektech.rickandmorty.presentation.app

import android.app.Application
import com.geektech.rickandmorty.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(listOf(appModule))
        }
    }
}