# android-spotify-catalog

An android project to list current top 10 artists from Spotify with its top albums and tracks from those albums

## Features

- **Spotify catalog:** List of current top ten artists from spotify, with album details and tracks.
- **Modern standards:** Using Hilt for DI, Jetpack compose for UI, Flows and coroutines for async task, between others 
- **Modern Architecture:** Uses Clean Architecture principles with a modular MVVM structure.

## Requirements

- Android Studio Flamingo (or later)
- Gradle 8.0+
- Minimum SDK: 30
- Target SDK: 33

## Installation

1. Install Android Studio: https://developer.android.com/studio/
2. Create a Wep API project from spotify: https://developer.spotify.com/documentation/web-api/tutorials/getting-started 
3. Clone the repository: git clone https://github.com/anfo18-prog/android-spotify-catalog
4. Open the project in Android Studio.
5. Set your secrets from Spotify (grant type, client id and client secret in local.properties):
´´´
   SPOTIFY_GRANT_TYPE=client_credentials
   SPOTIFY_CLIENT_ID=XXXXXXX
   SPOTIFY_CLIENT_SECRET=XXXXXXX
´´´
6. Change the list of artists to load in **app/src/main/assets/artists.json**
7. Run the app on an emulator or a physical device.

## Known issues
- **HTTP 400 Invalid bearer token:** Secrets in local.properties not added or not being loaded correctly
- **HTTP 401 not authorized:** Secrets in local.properties not correct, check with the ones provided from Spotify
