package com.leveloper.roomconverter.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.leveloper.roomconverter.data.converter.AddressTypeConverter
import com.leveloper.roomconverter.data.converter.StringListTypeConverter

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
@TypeConverters(
    value = [
        StringListTypeConverter::class,
        AddressTypeConverter::class
    ]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}