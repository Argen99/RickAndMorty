plugins {
    id(Plugins.AGP.application)
    id(Plugins.Kotlin.kotlin)
    id(Plugins.Kotlin.kotlinKapt)
}

android {
    compileSdk = AndroidConfig.compileSdk

    defaultConfig {
        applicationId = "com.geektech.rickandmorty"
        minSdk = AndroidConfig.minSdk
        targetSdk = AndroidConfig.targetSdk
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    // Implementation project
    implementation(project(":domain"))
    implementation(project(":data"))

    // UI
    implementation(Dependencies.UI.androidCore)
    implementation(Dependencies.UI.androidAppCompat)
    implementation(Dependencies.UI.androidMaterial)
    implementation(Dependencies.UI.androidConstraintLayout)
    implementation(Dependencies.UI.androidxActivity)
    implementation(Dependencies.UI.androidxFragment)
    implementation(Dependencies.UI.androidLegacy)
    testImplementation(Dependencies.UI.junit)
    androidTestImplementation(Dependencies.UI.androidTestJunit)
    androidTestImplementation(Dependencies.UI.androidTestEspresso)

    // Navigation component
    implementation(Dependencies.NavigationComponent.navigationFragment)
    implementation(Dependencies.NavigationComponent.navigationUiKtx)

    // Room
    implementation(Dependencies.Room.roomRunTime)
    implementation(Dependencies.Room.roomKtx)
    kapt(Dependencies.Room.roomCompiler)

    // LiveData
    implementation(Dependencies.LiveData.liveData)
    implementation(Dependencies.LiveData.viewModel)
    implementation(Dependencies.LiveData.runtime)

    // Coroutines
    implementation(Dependencies.Coroutines.coroutines)
    implementation(Dependencies.Coroutines.core)

    // Koin
    implementation(Dependencies.Koin.koinCore)
    implementation(Dependencies.Koin.koinAndroid)
    testImplementation(Dependencies.Koin.koinTest)

    // Paging
    implementation(Dependencies.Paging.pagingRuntime)

    // SwipeRefreshLayout
    implementation(Dependencies.SwipeRefreshLayout.swipeRefreshLayout)

    // Glide
    implementation(Dependencies.Glide.glide)
    kapt(Dependencies.Glide.glideCompiler)

    // ViewBindingDelegate
    implementation(Dependencies.ViewBindingDelegate.viewBindingDelegate)

    // Retrofit
    implementation(Dependencies.Retrofit.retrofit)
    implementation(Dependencies.Retrofit.retrofitConverterGson)
    implementation(Dependencies.Retrofit.okHttp)

    // Interceptor
    implementation(Dependencies.Interceptor.interceptor)
    implementation(Dependencies.SplashScreen.splashScreen)
}