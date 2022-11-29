package com.example.mynoteapp.presentation.core

import java.presentation.core.Resource

sealed class Resource<out T>(
    val data: T? = null,
    val message: String? = null,
) {
    class Loading<T>(data: T? = null) : Resource<T>(data = data)
    class Success<T>(data: T) : Resource<T>(data = data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data = data, message = message)
}