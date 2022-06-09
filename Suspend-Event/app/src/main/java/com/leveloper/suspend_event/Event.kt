package com.leveloper.suspend_event

sealed class Event

class DialogEvent(
    val message: String
) : Event(), EventDelegator<Boolean> by DelegatedEvent()

class ToastEvent(
    val message: String
) : Event()
