package com.geektech.data.remote.models.episode

import com.geektech.data.remote.models.InfoDto

data class EpisodesResponseDto(
    val info: InfoDto,
    val results: List<EpisodesDto>
)