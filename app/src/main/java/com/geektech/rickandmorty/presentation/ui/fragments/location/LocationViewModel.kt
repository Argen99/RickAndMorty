@file:OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)

package com.geektech.rickandmorty.presentation.ui.fragments.location

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.geektech.domain.models.location.Location
import com.geektech.domain.use_case.GetAllLocationsUseCase
import com.geektech.rickandmorty.core.base.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest

class LocationViewModel(
    private val getAllLocationsUseCase: GetAllLocationsUseCase
): BaseViewModel() {

    val locationsFlow: Flow<PagingData<Location>>
    private val searchBy = MutableStateFlow("")

    init {
        locationsFlow = searchBy.flatMapLatest {
            getAllLocationsUseCase.invoke(it)
                .debounce(500)
                .cachedIn(viewModelScope)
        }
    }

    fun searchBy(value: String) {
        if (searchBy.value == value) return
        searchBy.value = value
    }
}