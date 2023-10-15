package com.scdc.core.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

data class Failure(
    val error: Throwable? = null
)

abstract class BaseViewModel : ViewModel() {

    private val _failure = mutableStateOf(Failure())
    val failure: MutableState<Failure> = _failure

    protected fun handleFailure(failure: Throwable) {
        _failure.value = Failure(failure)
    }
}