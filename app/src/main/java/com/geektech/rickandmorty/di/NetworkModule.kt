package com.geektech.rickandmorty.di

import com.geektech.data.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

//val networkModule = module {
//    factory { provideOkHttpClient() }
//    factory { provideForecastApi(get()) }
//    single { provideRetrofit(get()) }
//}
//
//fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
//    return Retrofit.Builder()
//        .baseUrl(BuildConfig.BASE_URL)
//        .addConverterFactory(GsonConverterFactory.create())
//        .client(okHttpClient)
//        .build()
//}
//
//fun provideOkHttpClient(): OkHttpClient {
//    val interceptor = HttpLoggingInterceptor()
//    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
//    return OkHttpClient().newBuilder()
//        .addInterceptor(interceptor)
//        .connectTimeout(10, TimeUnit.SECONDS)
//        .readTimeout(10, TimeUnit.SECONDS)
//        .writeTimeout(10, TimeUnit.SECONDS)
//        .build()
//}
//
//fun provideForecastApi(retrofit: Retrofit): MangaReadApiService {
//    return retrofit.create(MangaReadApiService::class.java)
//}
