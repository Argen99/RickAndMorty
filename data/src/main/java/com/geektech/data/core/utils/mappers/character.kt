package com.geektech.data.core.utils.mappers

import com.geektech.data.remote.models.character.*
import com.geektech.domain.models.*

fun InfoDto.toModel() = Info(
    count = count,
    pages = pages,
    next = next,
    prev = prev
)

fun Info.toDto() = InfoDto(
    count = count,
    pages = pages,
    next = next,
    prev = prev
)

fun LocationDto.toModel() = Location(
    name = name,
    url = url
)

fun Location.toDto() = LocationDto(
    name = name,
    url = url
)

fun OriginDto.toModel() = Origin(
    name = name,
    url = url
)

fun Origin.toDto() = OriginDto(
    name = name,
    url = url
)

fun ResultDto.toModel() = Result(
    id = id,
    name = name,
    status = status,
    species = species,
    type = type,
    gender = gender,
    origin = origin.toModel(),
    location = location.toModel(),
    image = image,
    episode = episode,
    url = url,
    created = created
)

fun Result.toDto() = ResultDto(
    id = id,
    name = name,
    status = status,
    species = species,
    type = type,
    gender = gender,
    origin = origin.toDto(),
    location = location.toDto(),
    image = image,
    episode = episode,
    url = url,
    created = created
)

fun RickAndMortyResponseDto.toModel() = RickAndMortyResponse(
    info = info.toModel(),
    results = results.map {
        it.toModel()
    }
)

fun RickAndMortyResponse.toDto() = RickAndMortyResponseDto(
    info = info.toDto(),
    results = results.map {
        it.toDto()
    }
)