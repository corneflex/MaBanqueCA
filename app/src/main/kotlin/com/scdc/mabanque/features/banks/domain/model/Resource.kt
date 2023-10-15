package com.scdc.mabanque.features.banks.domain.model

sealed class Resource<out T> {
    data class Success<out T>(val data:T): Resource<T>()
    data class Error(val error: Throwable): Resource<Nothing>()
    object Loading : Resource<Nothing>()
}