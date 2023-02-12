package com.geektech.rickandmorty.presentation.app

import android.app.Application
import com.geektech.rickandmorty.di.appModule
import com.geektech.rickandmorty.di.dataModule
import com.geektech.rickandmorty.di.domainModule
import com.geektech.rickandmorty.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(listOf(appModule, dataModule, domainModule, networkModule))
        }
    }
}