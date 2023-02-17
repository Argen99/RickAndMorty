package com.geektech.domain.use_case

import com.geektech.domain.repository.MainRepository

class GetAllLocationsUseCase(
    private val repository: MainRepository
) {
    operator fun invoke(name: String) = repository.getAllLocations(name)
}