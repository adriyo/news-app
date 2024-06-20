package com.adriyo.newsapp.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class SourceDto(
    val id: String?,
    val name: String
)
