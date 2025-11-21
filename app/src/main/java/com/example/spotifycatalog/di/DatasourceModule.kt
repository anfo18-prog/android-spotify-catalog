package com.example.spotifycatalog.di

import com.example.spotifycatalog.data.remote.datasource.SpotifyRemoteDataSource
import com.example.spotifycatalog.data.remote.datasource.SpotifyRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindSpotifyRemoteDataSource(
        impl: SpotifyRemoteDataSourceImpl
    ): SpotifyRemoteDataSource
}