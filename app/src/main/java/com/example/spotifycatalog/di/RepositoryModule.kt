package com.example.spotifycatalog.di

import com.example.spotifycatalog.data.repository.SpotifyRepositoryImpl
import com.example.spotifycatalog.domain.repository.SpotifyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindSpotifyRepository(
        impl: SpotifyRepositoryImpl
    ): SpotifyRepository
}