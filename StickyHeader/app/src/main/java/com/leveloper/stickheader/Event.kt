package com.leveloper.stickheader

data class Event(
    val value: Int
) {
    val date: Int
        get() = value / 10

    val isHeader: Boolean
        get() = value % 10 == 0
}