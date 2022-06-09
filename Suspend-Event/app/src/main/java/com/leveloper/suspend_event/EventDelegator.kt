package com.leveloper.suspend_event

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.first
import kotlin.coroutines.cancellation.CancellationException

interface EventDelegator<T> {
    suspend fun result(): T

    fun tryEmit(value: T): Boolean

    fun cancel(): Boolean
}

class DelegatedEvent<T : Any> : EventDelegator<T> {

    private val flow = MutableSharedFlow<T?>(extraBufferCapacity = 1)

    override suspend fun result(): T = flow.first() ?: throw CancellationException()

    override fun tryEmit(value: T): Boolean = flow.tryEmit(value)

    override fun cancel(): Boolean = flow.tryEmit(null)
}