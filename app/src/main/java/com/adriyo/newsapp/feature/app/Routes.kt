package com.adriyo.newsapp.feature.app

import kotlinx.serialization.Serializable

@Serializable
sealed class Route {
    @Serializable
    data object News: Route()

    @Serializable
    data object Search: Route()

    @Serializable
    data class WebView(val url: String): Route()
}

