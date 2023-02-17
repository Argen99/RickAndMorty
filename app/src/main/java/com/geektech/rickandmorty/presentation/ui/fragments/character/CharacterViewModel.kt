@file:OptIn(FlowPreview::class)

package com.geektech.rickandmorty.presentation.ui.fragments.character

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.geektech.domain.models.characters.Character
import com.geektech.domain.use_case.GetAllCharactersUseCase
import com.geektech.rickandmorty.core.base.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

@OptIn(ExperimentalCoroutinesApi::class)
class CharacterViewModel(
    private val getAllCharactersUseCase: GetAllCharactersUseCase
): BaseViewModel() {

    val charactersFlow: Flow<PagingData<Character>>
    private val filterBy = MutableStateFlow("")
    private val searchBy = MutableStateFlow("")

    init {
        charactersFlow = combine(searchBy, filterBy) { search, filter ->
            Pair(search, filter)
        }.flatMapLatest {(search, filter) ->
            getAllCharactersUseCase.invoke(search ,filter )
                .debounce(500)
                .cachedIn(viewModelScope)
        }
    }

    fun filterBy(value: String) {
        if (this.filterBy.value == value) return
        this.filterBy.value = value
    }

    fun searchBy(value: String) {
        if (searchBy.value == value) return
        searchBy.value = value
    }
}