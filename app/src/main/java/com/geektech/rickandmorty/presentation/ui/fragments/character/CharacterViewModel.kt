@file:OptIn(FlowPreview::class)

package com.geektech.rickandmorty.presentation.ui.fragments.character

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.geektech.domain.models.FilterArguments
import com.geektech.domain.models.characters.Character
import com.geektech.domain.use_case.GetAllCharactersUseCase
import com.geektech.rickandmorty.core.base.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

@OptIn(ExperimentalCoroutinesApi::class)
class CharacterViewModel(
    private val getAllCharactersUseCase: GetAllCharactersUseCase
) : BaseViewModel() {

    val charactersFlow: Flow<PagingData<Character>>

    private val searchBy = MutableStateFlow("")
    private val filter = MutableSharedFlow<FilterArguments>(replay = 1)

    init {
        filter.tryEmit(FilterArguments("","",""))
        charactersFlow = combine(filter,searchBy) { filter, search ->
            Pair(filter, search)
        }.flatMapLatest {(filter, search) ->
            getAllCharactersUseCase.invoke(name = search, gender = filter.gender,
                status = filter.status,species = filter.species)
                .debounce(500)
                .cachedIn(viewModelScope)
        }
    }

    fun filterBy(status: String?,species: String?,gender: String?) {
        filter.tryEmit(
            FilterArguments(status,species,gender))
    }

    fun searchBy(value: String) {
        if (searchBy.value == value) return
        searchBy.value = value
    }
}