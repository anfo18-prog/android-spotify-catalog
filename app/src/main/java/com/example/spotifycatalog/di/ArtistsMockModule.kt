package com.example.spotifycatalog.di

import android.content.Context
import com.example.spotifycatalog.data.local.mock.ArtistsMockReader
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ArtistsMockModule {
    @Provides
    @Singleton
    fun provideArtistsMockReader(
        @ApplicationContext context: Context,
        moshi: Moshi
    ) = ArtistsMockReader(
        context, moshi
    )
}