package com.leveloper.shimmereffect

data class Sample(
    val name: String,
    val email: String
)

fun getSampleList(): List<Sample> {
    return listOf(
        Sample("Leveloper", "tkdgusl94@gmail.com"),
        Sample("Harry Potter", "harry@hogwarts.com"),
        Sample("Hermione Granger", "pretty@hogwarts.com"),
        Sample("Jeong Sang Hyun", "tkdgusl94@naver.com"),
        Sample("Ronald Bilius Weasley", "ron@hogwarts.com"),
        Sample("Mark Elliot Zuckerberg", "mark@facebook.com")
    )
}