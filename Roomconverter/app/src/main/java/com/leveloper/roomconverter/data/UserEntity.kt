package com.leveloper.roomconverter.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var name: String,
    var phones: List<String>,
    var address: Address
)

data class Address(
    var value: String,
    var zipcode: Int
)