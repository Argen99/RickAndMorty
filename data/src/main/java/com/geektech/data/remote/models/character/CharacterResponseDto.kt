package com.geektech.data.remote.models.character

import com.geektech.data.remote.models.InfoDto

data class ChracterResponseDto(
    val info: InfoDto,
    val results: List<CharacterDto>
)