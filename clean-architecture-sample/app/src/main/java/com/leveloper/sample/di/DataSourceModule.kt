package com.leveloper.sample.di

import com.leveloper.data.source.GithubRemoteSource
import com.leveloper.data.source.GithubRemoteSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun providesGithubRemoteSource(source: GithubRemoteSourceImpl): GithubRemoteSource {
        return source
    }
}