package com.example.spotifycatalog.di

import com.example.spotifycatalog.data.local.datasource.MockDatasource
import com.example.spotifycatalog.data.local.datasource.MockDatasourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ArtistsMockModule {
    @Binds
    @Singleton
    abstract fun provideArtistsMockDataSource(
        datasourceImpl: MockDatasourceImpl
    ): MockDatasource
}