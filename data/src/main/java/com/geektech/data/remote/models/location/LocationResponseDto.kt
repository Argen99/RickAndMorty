package com.geektech.data.remote.models.location

import com.geektech.data.remote.models.InfoDto

data class LocationResponseDto(
    val info: InfoDto,
    val results: List<LocationDto>
)