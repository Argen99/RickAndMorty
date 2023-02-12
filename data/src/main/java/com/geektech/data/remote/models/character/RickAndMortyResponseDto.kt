package com.geektech.data.remote.models.character

data class RickAndMortyResponseDto(
    val info: InfoDto,
    val results: List<ResultDto>
)