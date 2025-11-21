package com.example.spotifycatalog.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SpotifyClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthClient