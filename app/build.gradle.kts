import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.spotifycatalog"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.spotifycatalog"
        minSdk = 30
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // Secrets
        val localProps = project.rootProject.file("local.properties")
            .inputStream()
            .use { Properties().apply { load(it) } }

        buildConfigField(
            "String",
            "SPOTIFY_CLIENT_ID",
            "\"${localProps["SPOTIFY_CLIENT_ID"]}\""
        )
        buildConfigField(
            "String",
            "SPOTIFY_CLIENT_SECRET",
            "\"${localProps["SPOTIFY_CLIENT_SECRET"]}\""
        )
        buildConfigField(
            "String",
            "SPOTIFY_GRANT_TYPE",
            "\"${localProps["SPOTIFY_GRANT_TYPE"]}\""
        )
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }

    // Env settings
    flavorDimensions += "environment"
    productFlavors {
        create("dev") {
            dimension = "environment"
            buildConfigField("String", "SPOTIFY_API_URL", "\"https://api.spotify.com/\"")
            buildConfigField("String", "SPOTIFY_ACCOUNTS_URL", "\"https://accounts.spotify.com/\"")
        }
        create("prod") {
            dimension = "environment"
            buildConfigField("String", "SPOTIFY_API_URL", "\"https://api.spotify.com/\"")
            buildConfigField("String", "SPOTIFY_ACCOUNTS_URL", "\"https://accounts.spotify.com/\"")
        }
    }
}

dependencies {

    // Core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    // Coroutines
    implementation(libs.coroutines.android)

    // Hilt DI
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    ksp(libs.hilt.compiler)

    // Navigation
    implementation(libs.androidx.compose.navigation)

    // Retrofit, Http
    implementation(libs.retrofit)
    implementation(libs.retrofit.moshi)
    implementation(libs.okhttp.logging)

    // Serialization
    implementation(libs.moshi)
    implementation(libs.moshi.kotlin)
    ksp(libs.moshi.kotlin.codegen)

    // Storage
    implementation(libs.datastore.preferences)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.turbine)
    testImplementation(libs.coroutines.test)
    testImplementation(kotlin("test"))
}