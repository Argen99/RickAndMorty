package com.geektech.domain.use_case

import com.geektech.domain.repository.MainRepository

class GetAllCharactersUseCase(
    private val repository: MainRepository
) {
    operator fun invoke(name: String? ,status: String?) =
        repository.getAllCharacters(name = name, status = status)
}