package com.leveloper.viewmodel_extensions.di

import com.leveloper.viewmodel_extensions.DispatcherProvider
import com.leveloper.viewmodel_extensions.DispatcherProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface DispatcherModule {

    @Binds
    fun bindDispatcherProvider(provider: DispatcherProviderImpl): DispatcherProvider
}