package com.geektech.rickandmorty.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.geektech.domain.common.Resource
import com.geektech.rickandmorty.core.ui_state.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

abstract class BaseViewModel: ViewModel() {

    protected fun <T> Flow<Resource<List<T>>>.collectListFlow(_state: MutableStateFlow<UIState<List<T>>>) {
        viewModelScope.launch(Dispatchers.IO) {
            this@collectListFlow.collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.value = UIState.Loading()
                    }
                    is Resource.Error -> {
                        _state.value = UIState.Error(result.message!!)
                    }
                    is Resource.Success -> {
                        if (result.data != null) {
                            _state.value = UIState.Success(result.data!!)
                        }
                    }
                }
            }
        }
    }

    protected fun <T : Any, S : Any> Flow<PagingData<T>>.pagingRequest(
        mappedData: (data: T) -> S,
    ) = map {
        it.map { data ->
            mappedData(data)
        }
    }.cachedIn(viewModelScope)

    protected fun <T> Flow<Resource<T>>.collectFlow(_state: MutableStateFlow<UIState<T>>) {
        viewModelScope.launch(Dispatchers.IO) {
            this@collectFlow.collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.value = UIState.Loading()
                    }
                    is Resource.Error -> {
                        _state.value = UIState.Error(result.message!!)
                    }
                    is Resource.Success -> {
                        if (result.data != null) {
                            _state.value = UIState.Success(result.data!!)
                        }
                    }
                }
            }
        }
    }
}