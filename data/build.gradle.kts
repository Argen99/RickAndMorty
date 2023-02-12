plugins {
    id(Plugins.AGP.library)
    id(Plugins.Kotlin.kotlin)
    id(Plugins.Kotlin.kotlinKapt)
}

android {
    compileSdk = AndroidConfig.compileSdk

    defaultConfig {
        minSdk = AndroidConfig.minSdk
        targetSdk = AndroidConfig.targetSdk

        buildConfigField("String", "BASE_URL", "\"https://rickandmortyapi.com/\"")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {

    implementation(project(":domain"))

    // UI
    implementation(Dependencies.UI.androidCore)
    testImplementation(Dependencies.UI.junit)
    androidTestImplementation(Dependencies.UI.androidTestJunit)

    // Inject
    implementation(Dependencies.Koin.koinCore)
    implementation(Dependencies.Koin.koinAndroid)
    implementation(Dependencies.Koin.koinTest)

    // Paging
    implementation(Dependencies.Paging.pagingRuntime)

    // Retrofit
    implementation(Dependencies.Retrofit.retrofit)
    implementation(Dependencies.Retrofit.retrofitConverterGson)
    implementation(Dependencies.Retrofit.okHttp)

    // Room
    implementation(Dependencies.Room.roomRunTime)
    implementation(Dependencies.Room.roomKtx)
    kapt(Dependencies.Room.roomCompiler)

    // Coroutines
    implementation(Dependencies.Coroutines.coroutines)
    implementation(Dependencies.Coroutines.core)

    // Interceptor
    implementation(Dependencies.Interceptor.interceptor)
}