package com.geektech.domain.models.episodes

import com.geektech.domain.models.Info
import com.geektech.domain.models.episodes.Episodes

data class EpisodesResponse(
    val info: Info,
    val results: List<Episodes>
)