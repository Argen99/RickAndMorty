package com.geektech.rickandmorty.di

import com.geektech.rickandmorty.presentation.ui.fragments.character.CharacterViewModel
import com.geektech.rickandmorty.presentation.ui.fragments.episode.EpisodeViewModel
import com.geektech.rickandmorty.presentation.ui.fragments.location.LocationViewModel
import com.geektech.rickandmorty.presentation.ui.fragments.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<MainViewModel> {
        MainViewModel()
    }

    viewModel<CharacterViewModel> {
        CharacterViewModel()
    }

    viewModel<EpisodeViewModel> {
        EpisodeViewModel()
    }

    viewModel<LocationViewModel> {
        LocationViewModel()
    }
}