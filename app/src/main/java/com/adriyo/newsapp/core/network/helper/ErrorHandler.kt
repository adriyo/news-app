package com.adriyo.newsapp.core.network.helper

import com.adriyo.newsapp.core.network.model.ErrorResponse
import com.adriyo.newsapp.shared.util.Constants
import kotlinx.serialization.json.Json
import retrofit2.HttpException

fun Exception.getError(): Exception {
    if (this is HttpException) {
        return try {
            val response = this.response()?.errorBody()?.string() ?: ""
            val result = Json.decodeFromString<ErrorResponse>(response)
            return Exception(result.message)
        } catch (exception: Exception) {
            Exception(Constants.ERROR_REQUEST_FAILED)
        }
    }
    return Exception(Constants.ERROR_REQUEST_FAILED)
}