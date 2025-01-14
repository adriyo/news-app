package com.adriyo.newsapp.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val code: String,
    val message: String,
    val status: String
)