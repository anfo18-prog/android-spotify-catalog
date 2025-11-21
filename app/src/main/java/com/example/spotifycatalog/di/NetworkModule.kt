package com.example.spotifycatalog.di

import android.content.Context
import com.example.spotifycatalog.data.remote.api.AuthApi
import com.example.spotifycatalog.data.remote.api.SpotifyApi
import com.example.spotifycatalog.data.remote.manager.TokenManager
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import com.example.spotifycatalog.BuildConfig
import com.example.spotifycatalog.data.local.storage.TokenStorage
import com.example.spotifycatalog.data.remote.api.AuthApiSync
import com.example.spotifycatalog.data.remote.auth.TokenAuthenticator
import com.example.spotifycatalog.data.remote.interceptor.TokenInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides @Singleton
    fun provideTokenStorage(@ApplicationContext context: Context): TokenStorage = TokenStorage(context)

    @Provides @Singleton
    fun provideTokenManager(storage: TokenStorage): TokenManager = TokenManager(storage)


    @AuthClient
    @Provides
    @Singleton
    fun provideAuthOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

    @SpotifyClient
    @Provides
    @Singleton
    fun provideOkHttp(
        tokenManager: TokenManager,
        authApiSync: AuthApiSync,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(TokenInterceptor(
                tokenManager,
                authApiSync,
                BuildConfig.SPOTIFY_CLIENT_ID,
                BuildConfig.SPOTIFY_CLIENT_SECRET)
            )
            .authenticator(
                TokenAuthenticator(
                    tokenManager,
                    authApiSync,
                    BuildConfig.SPOTIFY_CLIENT_ID,
                    BuildConfig.SPOTIFY_CLIENT_SECRET,
                )
            )
            .build()

    @Provides @Singleton
    fun provideAuthApi(
        @AuthClient authOkHttp: OkHttpClient,
        moshi: Moshi
    ): AuthApi =
        Retrofit.Builder()
            .baseUrl(BuildConfig.SPOTIFY_ACCOUNTS_URL)
            .client(authOkHttp)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(AuthApi::class.java)

    @Provides @Singleton
    fun provideAuthApiSync(
        @AuthClient authOkHttp: OkHttpClient,
        moshi: Moshi
    ): AuthApiSync =
        Retrofit.Builder()
            .baseUrl(BuildConfig.SPOTIFY_ACCOUNTS_URL)
            .client(authOkHttp)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(AuthApiSync::class.java)

    @Provides @Singleton
    fun provideSpotifyApi(
        @SpotifyClient okHttp: OkHttpClient,
        moshi: Moshi
    ): SpotifyApi =
        Retrofit.Builder()
            .baseUrl(BuildConfig.SPOTIFY_API_URL)
            .client(okHttp)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(SpotifyApi::class.java)
}