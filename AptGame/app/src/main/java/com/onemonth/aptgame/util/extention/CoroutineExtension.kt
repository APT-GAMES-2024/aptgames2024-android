package com.onemonth.aptgame.util.extention

import kotlinx.coroutines.CancellableContinuation
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume

inline fun <T> Continuation<T>.safeResume(value: T, onExceptionCalled: () -> Unit) {
    if (this is CancellableContinuation) {
        if (isActive)
            resume(value)
        else
            onExceptionCalled()
    } else throw Exception("Must use suspendCancellableCoroutine instead of suspendCoroutine")
}

inline fun <T> Continuation<T>.safeResume(value: T) {
    if (this is CancellableContinuation) {
        if (isActive)
            resume(value)
    } else throw Exception("Must use suspendCancellableCoroutine instead of suspendCoroutine")
}