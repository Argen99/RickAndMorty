package com.geektech.rickandmorty.presentation.ui.fragments.character

import com.geektech.domain.models.RickAndMortyResponse
import com.geektech.domain.use_case.GetAllCharactersUseCase
import com.geektech.rickandmorty.core.base.BaseViewModel
import com.geektech.rickandmorty.core.ui_state.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CharacterViewModel(
    private val getAllCharactersUseCase: GetAllCharactersUseCase
): BaseViewModel() {

    private val _getAllCharactersState = MutableStateFlow<UIState<RickAndMortyResponse>>(UIState.Empty())
    val getAllCharactersState = _getAllCharactersState.asStateFlow()

    fun getAllCharacters(page: Int? = null, status: String? = null) {
        getAllCharactersUseCase.invoke(page, status).collectFlow(_getAllCharactersState)
    }
}