package com.geektech.domain.models.characters

import com.geektech.domain.models.Info

data class RickAndMortyResponse(
    val info: Info,
    val results: List<Character>
)