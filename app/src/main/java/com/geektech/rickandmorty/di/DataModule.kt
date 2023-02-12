package com.geektech.rickandmorty.di

import com.geektech.data.remote.api_service.RickAndMortyApiService
import com.geektech.data.remote.repository.CharacterRepositoryImpl
import com.geektech.domain.repository.CharacterRepository
import org.koin.dsl.module

val dataModule = module {

    single<CharacterRepository> {
        CharacterRepositoryImpl(
            apiService = get<RickAndMortyApiService>())
    }
}