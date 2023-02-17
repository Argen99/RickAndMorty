package com.geektech.data.core.utils.mappers

import com.geektech.data.remote.models.InfoDto
import com.geektech.data.remote.models.character.*
import com.geektech.domain.models.*
import com.geektech.domain.models.characters.Character
import com.geektech.domain.models.characters.CharacterLocation
import com.geektech.domain.models.characters.Origin
import com.geektech.domain.models.characters.RickAndMortyResponse

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

fun CharacterLocationDto.toModel() = CharacterLocation(
    name = name,
    url = url
)

fun CharacterLocation.toDto() = CharacterLocationDto(
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

fun CharacterDto.toModel() = Character(
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

fun Character.toDto() = CharacterDto(
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

fun CharacterResponseDto.toModel() = RickAndMortyResponse(
    info = info.toModel(),
    results = results.map {
        it.toModel()
    }
)

fun RickAndMortyResponse.toDto() = CharacterResponseDto(
    info = info.toDto(),
    results = results.map {
        it.toDto()
    }
)