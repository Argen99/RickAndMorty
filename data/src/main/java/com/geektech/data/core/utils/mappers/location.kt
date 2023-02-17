package com.geektech.data.core.utils.mappers

import com.geektech.data.remote.models.location.LocationDto
import com.geektech.data.remote.models.location.LocationResponseDto
import com.geektech.domain.models.location.Location
import com.geektech.domain.models.location.LocationResponse

fun LocationResponseDto.toModel() = LocationResponse(
    info = info.toModel(),
    results = results.map { it.toModel() }
)

fun LocationResponse.toDto() = LocationResponseDto(
    info = info.toDto(),
    results = results.map { it.toDto() }
)

fun LocationDto.toModel() = Location(
    id = id,
    name = name,
    type = type,
    dimension = dimension,
    residents = residents,
    url = url,
    created = created
)

fun Location.toDto() = LocationDto(
    id = id,
    name = name,
    type = type,
    dimension = dimension,
    residents = residents,
    url = url,
    created = created
)