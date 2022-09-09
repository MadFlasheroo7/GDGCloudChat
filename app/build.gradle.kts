import xyz.jayeshseth.gdgcloudchat.buildsrc.Libs
import xyz.jayeshseth.gdgcloudchat.buildsrc.Versions

plugins {
    id ("com.android.application")
    id ("org.jetbrains.kotlin.android")
    id ("kotlin-android")
    id ("com.google.gms.google-services")
    id ("kotlin-kapt")
    id ("dagger.hilt.android.plugin")
    id ("com.google.dagger.hilt.android")
}

android {
    compileSdk = 33

    defaultConfig {
        applicationId = "xyz.jayeshseth.gdgcloudchat"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"),
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
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose_compiler
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "kotlin/**"
        }
    }
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions.freeCompilerArgs += "-opt-in=kotlin.RequiresOptIn"
    }
}

dependencies {
    // kotlin
    implementation (Libs.Kotlin.stdlib)
    implementation (Libs.Kotlin.ktx)
    implementation (Libs.Kotlin.extensions)
    implementation (Libs.Kotlin.playServices)

    // coroutine
    implementation (Libs.Coroutines.core)
    implementation (Libs.Coroutines.kotlinx)

    // compose
    implementation (Libs.AndroidX.Compose.ui)
    implementation (Libs.AndroidX.Compose.layout)
    implementation (Libs.AndroidX.Compose.foundation)
    implementation (Libs.AndroidX.Compose.liveData)
    implementation (Libs.AndroidX.Compose.activity)
    implementation (Libs.AndroidX.Compose.activityKtx)
    implementation (Libs.AndroidX.Compose.materialIconsExtended)
    implementation (Libs.AndroidX.Compose.material)
    implementation (Libs.AndroidX.Compose.material3)
    implementation (Libs.AndroidX.Compose.googleMaterial)
    implementation (Libs.AndroidX.Compose.googleFonts)
    implementation (Libs.AndroidX.Compose.toolingPreview)
    implementation (Libs.AndroidX.Compose.ui_util)
    implementation (Libs.AndroidX.Compose.viewBinding)

    // accompanist
    implementation (Libs.Accompanist.systemUIController)
    implementation (Libs.Accompanist.navAnimation)

    // navigation
    implementation (Libs.AndroidX.Navigation.navigation)
    implementation (Libs.AndroidX.Navigation.navigationUiKtx)
    implementation (Libs.AndroidX.Navigation.fragment)

    //firebase
    implementation (platform(Libs.Firebase.Bom))
    implementation (Libs.Firebase.analytics)
    implementation (Libs.Firebase.auth)
    implementation (Libs.Firebase.playServicesAuth)
    implementation (Libs.Firebase.fireStore)
    implementation (Libs.Firebase.realTimeDB)
    implementation (Libs.Firebase.notification)

    // lifecycle
    implementation (Libs.AndroidX.LifeCycle.viewModel)
    implementation (Libs.AndroidX.LifeCycle.viewModelCompose)
    implementation (Libs.AndroidX.LifeCycle.liveData)
    implementation (Libs.AndroidX.LifeCycle.runtime)
    implementation (Libs.AndroidX.LifeCycle.viewModelKtx)

    // Hilt
    implementation (Libs.Hilt.hiltAndroid)
    kapt (Libs.Hilt.hiltCompiler)
    kapt (Libs.Hilt.androidCompiler)
//    implementation (Libs.Hilt.viewModel)
    implementation (Libs.Hilt.navigation)

    // navigation
    implementation (Libs.AndroidX.Navigation.navigation)
    implementation (Libs.AndroidX.Navigation.navigationUiKtx)
    implementation (Libs.AndroidX.Navigation.fragment)

    // others
    implementation (Libs.AndroidX.Others.appcompat)

//    testImplementation 'junit:junit:4.13.2'
//    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
//    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'
//    androidTestImplementation "androidx.compose.ui:ui-test-junit4:1.2.1"

    // debug
    debugImplementation (Libs.AndroidX.Compose.uiTooling)
    debugImplementation (Libs.AndroidX.Compose.composeManifest)

}