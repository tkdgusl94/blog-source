package com.leveloper.paging3

sealed class SampleModel(val type: SampleType) {
    data class Data(val value: String): SampleModel(SampleType.DATA)
    data class Header(val title: String): SampleModel(SampleType.HEADER)
    object Separator: SampleModel(SampleType.SEPARATOR)
}

enum class SampleType {
    HEADER, DATA, SEPARATOR
}