@file:OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)

package com.geektech.rickandmorty.presentation.ui.fragments.episode

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.geektech.domain.models.episodes.Episodes
import com.geektech.domain.use_case.GetAllEpisodesUseCase
import com.geektech.rickandmorty.core.base.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest

class EpisodeViewModel(
    private val getAllEpisodesUseCase: GetAllEpisodesUseCase
): BaseViewModel() {

    val episodesFlow: Flow<PagingData<Episodes>>
    private val searchBy = MutableStateFlow("")

    init {
        episodesFlow = searchBy.flatMapLatest {
            getAllEpisodesUseCase.invoke(it)
                .debounce(500)
                .cachedIn(viewModelScope)
        }
    }

    fun searchBy(value: String) {
        if (searchBy.value == value) return
        searchBy.value = value
    }
}