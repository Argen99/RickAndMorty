object Dependencies {
    object UI {
        const val androidCore = "androidx.core:core-ktx:${Versions.androidCore}"
        const val androidAppCompat = "androidx.appcompat:appcompat:${Versions.androidAppCompat}"
        const val androidMaterial =
            "com.google.android.material:material:${Versions.androidMaterial}"
        const val androidConstraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.androidConstraintLayout}"
        const val androidxActivity = "androidx.activity:activity-ktx:${Versions.androidxActivity}"
        const val androidxFragment = "androidx.fragment:fragment-ktx:${Versions.androidxFragment}"
        const val androidLegacy = "androidx.legacy:legacy-support-v4:${Versions.androidLegacy}"
        const val junit = "junit:junit:${Versions.junit}"
        const val androidTestJunit = "androidx.test.ext:junit:${Versions.androidTestJunit}"
        const val androidTestEspresso =
            "androidx.test.espresso:espresso-core:${Versions.androidTestEspresso}"
    }
    object NavigationComponent {
        const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigationComponent}"
        const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigationComponent}"
    }

    object Room {
        const val roomRunTime = "androidx.room:room-runtime:${Versions.room}"
        const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
        const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    }

    object LiveData {
        const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.liveData}"
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.liveData}"
        const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.liveData}"
    }

    object Coroutines {
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    }

    object Koin {
        const val koinCore = "io.insert-koin:koin-core:${Versions.kotlinKoin}"
        const val koinAndroid = "io.insert-koin:koin-android:${Versions.kotlinKoin}"
        const val koinTest = "io.insert-koin:koin-test:${Versions.kotlinKoin}"
    }

    object Paging {
        const val pagingRuntime = "androidx.paging:paging-runtime-ktx:${Versions.pagingRuntime}"
        const val pagingCommon = "androidx.paging:paging-common:${Versions.pagingRuntime}"
    }

    object SwipeRefreshLayout {
        const val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefreshLayout}"
    }

    object Glide {
        const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
        const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
    }

    object ViewBindingDelegate {
        const val viewBindingDelegate = "com.github.kirich1409:viewbindingpropertydelegate-noreflection:${Versions.viewBindingDelegate}"
    }

    object Retrofit {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val retrofitConverterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
        const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
    }

    object Interceptor {
        const val interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.interceptor}"
    }

    object SplashScreen {
        const val splashScreen = "androidx.core:core-splashscreen:${Versions.splashScreen}"
    }
}

object Plugins {
    object AGP {
        const val application = "com.android.application"
        const val library = "com.android.library"
    }

    object Kotlin {
        const val kotlin = "org.jetbrains.kotlin.android"
        const val jvm = "org.jetbrains.kotlin.jvm"
        const val kotlinKapt = "kotlin-kapt"
    }

    object Java {
        const val javaLibrary = "java-library"
    }
}

object Versions {
    // UI
    const val androidCore = "1.7.0"
    const val androidAppCompat = "1.5.1"
    const val androidMaterial = "1.7.0"
    const val androidConstraintLayout = "2.1.4"
    const val androidxActivity = "1.5.1"
    const val androidxFragment = "1.5.2"
    const val androidLegacy = "1.0.0"
    const val junit = "4.13.2"
    const val androidTestJunit = "1.1.5"
    const val androidTestEspresso = "3.5.1"

    // Navigation component
    const val navigationComponent = "2.5.3"

    // Room
    const val room = "2.4.3"

    // LiveData
    const val liveData = "2.5.1"

    // Coroutines
    const val coroutines = "1.6.4"

    // Kotlin Koin
    const val kotlinKoin = "3.1.2"

    // Paging
    const val pagingRuntime = "3.1.1"

    // SwipeRefreshLayout
    const val swipeRefreshLayout = "1.1.0"

    // Glide
    const val glide = "4.14.1"

    // ViewBindingDelegate
    const val viewBindingDelegate = "1.5.6"

    // Retrofit
    const val retrofit = "2.9.0"

    // okHttp
    const val okHttp = "4.2.1"

    // Interceptor
    const val interceptor = "5.0.0-alpha.2"

    // AGP
    const val AGP = "7.2.2"

    // Kotlin
    const val kotlin = "1.7.10"

    // SplashScreen
    const val splashScreen = "1.0.0"
}