package com.meli.melichalllenge.data

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class Result<out T : Any> {
    object NoData: Result<Nothing>()
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val throwable: Throwable) : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<T> -> "Success[data=$data]"
            is Error -> "Error[exception=$throwable]"
            NoData -> "Empty"
        }
    }
}