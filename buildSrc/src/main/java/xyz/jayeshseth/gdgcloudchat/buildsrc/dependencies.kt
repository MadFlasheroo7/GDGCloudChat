package xyz.jayeshseth.gdgcloudchat.buildsrc

object Versions {
    const val accompanist = "0.25.1"
    const val androidGradlePlugin = "7.2.2"
    const val androidLibrary = "7.2.2"
    const val androidx_activity_compose = "1.5.1"
    const val androidx_appcompat = "1.5.0"
    const val androidx_constraintlayout = "1.0.1"
    const val androidx_corektx = "1.8.0"
    const val androidx_lifecycle_compose = "2.5.1"
    const val androidx_navigation = "2.5.1"
    const val androidx_window = "1.1.0-alpha03"
    const val androidxHiltNavigationCompose = "1.0.0"
    const val androidx_test_uiautomator = "2.2.0"
    const val coil = "2.2.0"
    const val compose = "1.2.1"
    const val compose_compiler = "1.3.0"
    const val compose_material3 = "1.0.0-alpha15"
    const val compose_materialWindow = "1.0.0-alpha15"
    const val compose_snapshot = "-"
    const val coroutines = "1.6.4"
    const val jdkDesugar = "1.1.5"
    const val junit = "4.13.2"
    const val kotlin = "1.7.10"
    const val material = "1.7.0-beta01"
    const val firebaseBom = "30.3.2"
    const val googleServices = "4.3.13"
    const val playServicesAuth = "20.2.0"
    const val hilt = "2.43.2"
    const val hiltViewModel = "1.0.0-alpha03"
}

object Libs {
    object Kotlin {
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
        const val extensions = "org.jetbrains.kotlin:kotlin-android-extensions:${Versions.kotlin}"
        const val ktx = "androidx.core:core-ktx:${Versions.androidx_corektx}"
        const val playServices = "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.1.1"
    }

    object Coroutines {
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        const val playService = "org.jetbrains.kotlinx:kotlinx-coroutines-play-service"
        const val kotlinx =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    }

    object Accompanist {
        const val systemUIController =
            "com.google.accompanist:accompanist-systemuicontroller:${Versions.accompanist}"
        const val insets = "com.google.accompanist:accompanist-insets:${Versions.accompanist}"
        const val navAnimation =
            "com.google.accompanist:accompanist-navigation-animation:${Versions.accompanist}"
        const val swiperefresh =
            "com.google.accompanist:accompanist-swiperefresh:${Versions.accompanist}"
    }

    object GradlePlugin {
        const val android = "com.android.tools.build:gradle:${Versions.androidGradlePlugin}"
        const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
        const val hilt = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
        const val googleServices = "com.google.gms:google-services:${Versions.googleServices}"
        const val androidLibrary = "com.android.library:${Versions.androidLibrary}"
    }

    object Hilt {
        const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hilt}"
        const val navigation =
            "androidx.hilt:hilt-navigation-compose:${Versions.androidxHiltNavigationCompose}"
        const val androidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
        const val hiltCompiler = "androidx.hilt:hilt-compiler:1.0.0"
        const val viewModel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hiltViewModel}"
    }


    /**
     *  more libraries here -
     *  https://firebase.google.com/docs/android/setup#available-libraries
     */
    object Firebase {
        const val Bom = "com.google.firebase:firebase-bom:${Versions.firebaseBom}"
        const val analytics = "com.google.firebase:firebase-analytics-ktx"
        const val auth = "com.google.firebase:firebase-auth-ktx"
        const val playServicesAuth =
            "com.google.android.gms:play-services-auth:${Versions.playServicesAuth}"
        const val fireStore = "com.google.firebase:firebase-firestore-ktx"
        const val realTimeDB = "com.google.firebase:firebase-database-ktx"
        const val notification = "com.google.firebase:firebase-messaging-ktx"
    }

    object AndroidX {
        object Compose {
            const val snapshot = ""
            const val m3snapshot = ""
            const val runtime = "androidx.compose.runtime:runtime:${Versions.compose}"
            const val foundation = "androidx.compose.foundation:foundation:${Versions.compose}"
            const val layout = "androidx.compose.foundation:foundation-layout:${Versions.compose}"
            const val activity =
                "androidx.activity:activity-compose:${Versions.androidx_activity_compose}"
            const val activityKtx = "androidx.activity:activity-ktx:1.5.1"
            const val liveData = "androidx.compose.runtime:runtime-livedata:${Versions.compose}"

            const val ui = "androidx.compose.ui:ui:${Versions.compose}"
            const val ui_util = "androidx.compose.ui:ui-util:${Versions.compose}"
            const val window = "androidx.window:window:${Versions.androidx_window}"
            const val viewBinding = "androidx.compose.ui:ui-viewbinding:${Versions.compose}"
            const val googleFonts = "androidx.compose.ui:ui-text-google-fonts:${Versions.compose}"
            const val constraintLayout =
                "androidx.constraintlayout:constraintlayout-compose:${Versions.androidx_constraintlayout}"
            const val material = "androidx.compose.material:material:${Versions.compose}"
            const val material3 =
                "androidx.compose.material3:material3:${Versions.compose_material3}"
            const val materialWindow =
                "androidx.compose.material3:material3-window-size-class:${Versions.compose_materialWindow}"
            const val googleMaterial = "com.google.android.material:material:${Versions.material}"
            const val materialIconsExtended =
                "androidx.compose.material:material-icons-extended:${Versions.compose}"

            const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
            const val toolingPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
            const val composeManifest = "androidx.compose.ui:ui-test-manifest:${Versions.compose}"
        }

        object Navigation {
            const val navigation =
                "androidx.navigation:navigation-compose:${Versions.androidx_navigation}"
            const val navigationUiKtx =
                "androidx.navigation:navigation-ui-ktx:${Versions.androidx_navigation}"
            const val fragment =
                "androidx.navigation:navigation-fragment-ktx:${Versions.androidx_navigation}"
        }

        object LifeCycle {
            const val viewModel =
                "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.androidx_lifecycle_compose}"
            const val liveData =
                "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.androidx_lifecycle_compose}"
            const val runtime =
                "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.androidx_lifecycle_compose}"
            const val viewModelCompose =
                "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.androidx_lifecycle_compose}"
            const val viewModelKtx =
                "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.androidx_lifecycle_compose}"
        }

        object DataStore {
            const val typed = "androidx.datastore:datastore:1.0.0"
            const val preference = "androidx.datastore:datastore-preferences:1.0.0"
        }

        object Others {
            const val coil = "io.coil-kt:coil-compose:${Versions.coil}"
            const val appcompat = "androidx.appcompat:appcompat:${Versions.androidx_appcompat}"
            const val splashScreen = "androidx.core:core-splashscreen:1.0.0-beta02"
        }

        object Test {
            object AndroidInstrumental {
                const val junit = "junit:junit:4.13.2"
                const val androidxJunit = "androidx.test.ext:junit:1.1.3"
                const val espresso = "androidx.test.espresso:espresso-core:3.4.0"
                const val UIJunit = "androidx.compose.ui:ui-test-junit4:1.1.1"
                const val truth = "androidx.arch.core:core-testing:2.1.0"
                const val core = "com.google.truth:truth:1.1.3"
                const val runner = "androidx.test:runner:1.4.0"
            }

            object Unit {
                const val junit = "junit:junit:4.13.2"
                const val core = "androidx.test:core:1.4.0"
                const val coreTesting = "androidx.arch.core:core-testing:2.1.0"
                const val truth = "com.google.truth:truth:1.1.3"
                const val roomTesting = "androidx.room:room-testing"
            }
        }
    }

    object Urls {
        const val composeSnapshotRepo = "https://androidx.dev/snapshots/builds/" +
                "${Libs.AndroidX.Compose.snapshot}/artifacts/repository/"
        const val composeMaterial3SnapshotRepo = "https://androidx.dev/snapshots/builds/" +
                "${Libs.AndroidX.Compose.m3snapshot}/artifacts/repository/"
        const val accompanistSnapshotRepo =
            "https://oss.sonatype.org/content/repositories/snapshots"
    }
}