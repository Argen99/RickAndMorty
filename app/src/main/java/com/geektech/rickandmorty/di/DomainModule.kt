package com.geektech.rickandmorty.di

import com.geektech.domain.repository.MainRepository
import com.geektech.domain.use_case.GetAllCharactersUseCase
import com.geektech.domain.use_case.GetAllEpisodesUseCase
import com.geektech.domain.use_case.GetAllLocationsUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<GetAllCharactersUseCase> {
        GetAllCharactersUseCase(repository = get<MainRepository>())
    }
    factory<GetAllEpisodesUseCase> {
        GetAllEpisodesUseCase(repository = get<MainRepository>())
    }

    factory<GetAllLocationsUseCase> {
        GetAllLocationsUseCase(repository = get<MainRepository>())
    }
}