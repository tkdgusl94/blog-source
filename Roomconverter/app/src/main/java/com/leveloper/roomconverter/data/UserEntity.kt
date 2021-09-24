package com.leveloper.roomconverter.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val name: String,
    val phones: List<String>,
    val address: Address
)

data class Address(
    val value: String,
    val zipcode: Int
)