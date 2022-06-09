package com.leveloper.suspend_event

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _event = MutableSharedFlow<Event>()
    val event = _event.asSharedFlow()

    private suspend fun emitEvent(event: Event) {
        _event.emit(event)
    }

    private suspend fun <T> awaitEvent(event: EventDelegator<T>): T {
        if (event is Event) {
            emitEvent(event)
        }

        return withContext(coroutineContext) {
            event.result()
        }
    }

    fun onClickSignOut() {
        viewModelScope.launch {
            val isOk = awaitEvent(
                DialogEvent(
                    message = "로그아웃 하시겠습니까?"
                )
            )

            if (isOk) signOut()
        }
    }

    private suspend fun signOut() {
        doSomething()

        emitEvent(
            ToastEvent(
                "로그아웃이 되었습니다."
            )
        )
    }

    private suspend fun doSomething() {}
}
