package com.example.mynoteapp.presentation.core

import java.presentation.core.UIState

sealed class UIState<T> {
    class Loading<T> : UIState<T>()
    class Error<T>(val error: String) : UIState<T>()
    class Success<T>(val data: T) : UIState<T>()
    class Empty<T> : UIState<T>()
}