package com.onemonth.aptgame.view.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

abstract class BaseViewModel : ViewModel() {
    sealed class Result<out R> {
        data class Success<out T>(val data: T) : Result<T>()
        object Loading : Result<Nothing>()
        data class Error(val throwable: Throwable) : Result<Nothing>()
    }

    fun <T> Flow<T>.asResult(): Flow<Result<T>> {
        return this
            .map<T, Result<T>> {
                Result.Success(it)
            }
            .catch { emit(Result.Error(it)) }
    }
}
