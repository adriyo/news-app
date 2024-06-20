package com.adriyo.newsapp.shared.util

import kotlinx.coroutines.CoroutineDispatcher

interface CoroutineDispatchers {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
}