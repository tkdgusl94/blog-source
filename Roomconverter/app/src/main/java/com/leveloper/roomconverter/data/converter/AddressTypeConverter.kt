package com.leveloper.roomconverter.data.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.leveloper.roomconverter.data.Address

@ProvidedTypeConverter
class AddressTypeConverter(
    private val gson: Gson
) {

    @TypeConverter
    fun listToJson(value: Address): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): Address {
        return gson.fromJson(value, Address::class.java)
    }
}