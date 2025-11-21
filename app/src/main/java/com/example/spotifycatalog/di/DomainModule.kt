package com.example.spotifycatalog.di

import com.example.spotifycatalog.domain.repository.SpotifyRepository
import com.example.spotifycatalog.domain.usecase.GetAlbumsForArtistUseCase
import com.example.spotifycatalog.domain.usecase.GetArtistUseCase
import com.example.spotifycatalog.domain.usecase.GetArtistsUseCase
import com.example.spotifycatalog.domain.usecase.GetTracksForAlbumUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Provides
    @Singleton
    fun provideGetArtistsUseCase(repository: SpotifyRepository) =
        GetArtistsUseCase(repository)

    @Provides @Singleton
    fun provideGetArtistUseCase(repository: SpotifyRepository) =
        GetArtistUseCase(repository)

    @Provides @Singleton
    fun provideGetAlbumsForArtistUseCase(repository: SpotifyRepository) =
        GetAlbumsForArtistUseCase(repository)

    @Provides @Singleton
    fun provideGetTracksForAlbumUseCase(repository: SpotifyRepository) =
        GetTracksForAlbumUseCase(repository)
}