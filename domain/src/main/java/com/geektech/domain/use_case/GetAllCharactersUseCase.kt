package com.geektech.domain.use_case

import com.geektech.domain.repository.CharacterRepository

class GetAllCharactersUseCase(
    private val repository: CharacterRepository
) {
    operator fun invoke(page: Int?, status: String?) = repository.getAllCharacters(page, status)
}