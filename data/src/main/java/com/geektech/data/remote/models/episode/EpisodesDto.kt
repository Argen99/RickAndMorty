package com.geektech.data.remote.models.episode

data class EpisodesDto(
    val id: Int,
    val name: String,
    val air_date: String,
    val episode: String,
    val characters: List<String>,
    val url: String,
    val created: String
)