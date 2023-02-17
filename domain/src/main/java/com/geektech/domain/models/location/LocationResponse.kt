package com.geektech.domain.models.location

import com.geektech.domain.models.Info

data class LocationResponse(
    val info: Info,
    val results: List<Location>
)