package com.geektech.data.core.utils.mappers

import com.geektech.data.remote.models.InfoDto
import com.geektech.data.remote.models.episode.EpisodesDto
import com.geektech.data.remote.models.episode.EpisodesResponseDto
import com.geektech.domain.models.episodes.Episodes
import com.geektech.domain.models.episodes.EpisodesResponse

fun EpisodesResponseDto.toModel() = EpisodesResponse(
    info = info.toModel(),
    results = results.map { it.toModel() }
)

fun EpisodesResponse.toDto() = EpisodesResponseDto(
    info = info.toDto(),
    results = results.map { it.toDto() }
)

fun EpisodesDto.toModel() = Episodes(
    id = id,
    name = name,
    air_date = air_date,
    episode = episode,
    characters = characters,
    url = url,
    created = created
)

fun Episodes.toDto() = EpisodesDto(
    id = id,
    name = name,
    air_date = air_date,
    episode = episode,
    characters = characters,
    url = url,
    created = created
)