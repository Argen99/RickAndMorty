package com.geektech.rickandmorty.di

import com.geektech.domain.repository.CharacterRepository
import com.geektech.domain.use_case.GetAllCharactersUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<GetAllCharactersUseCase> {
        GetAllCharactersUseCase(repository = get<CharacterRepository>())
    }
}