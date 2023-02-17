package com.geektech.rickandmorty.di

import com.geektech.data.remote.api_service.RickAndMortyApiService
import com.geektech.data.remote.repository.MainRepositoryImpl
import com.geektech.domain.repository.MainRepository
import org.koin.dsl.module

val dataModule = module {

    single<MainRepository> {
        MainRepositoryImpl(
            apiService = get<RickAndMortyApiService>())
    }
}