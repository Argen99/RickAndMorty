package com.geektech.data.remote.models

data class InfoDto(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)