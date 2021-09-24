package com.leveloper.roomconverter.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.leveloper.roomconverter.data.AppDatabase
import com.leveloper.roomconverter.data.UserDao
import com.leveloper.roomconverter.data.converter.AddressTypeConverter
import com.leveloper.roomconverter.data.converter.StringListTypeConverter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private const val DB_NAME = "sample.db"

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context, gson: Gson): AppDatabase {
        return Room
            .databaseBuilder(context, AppDatabase::class.java, DB_NAME)
            .addTypeConverter(StringListTypeConverter(gson)) // 'List<String>' converter
            .addTypeConverter(AddressTypeConverter(gson)) // 'Address' converter
            .build()
    }

    @Singleton
    @Provides
    fun provideUserDao(database: AppDatabase): UserDao {
        return database.userDao()
    }
}