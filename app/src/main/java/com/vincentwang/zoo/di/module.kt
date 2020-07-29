package com.vincentwang.zoo.di

import com.google.gson.Gson
import com.vincentwang.zoo.model.ZooRepository
import com.vincentwang.zoo.model.ZooServices
import com.vincentwang.zoo.ui.intro.IntroVM
import com.vincentwang.zoo.ui.plant.PlantVM
import com.vincentwang.zoo.util.AppDispatcherProvider
import com.vincentwang.zoo.util.CoroutineDispatcherProvider
import com.vincentwang.zoo.util.LoggerInterceptor
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.Protocol
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

val zooModule = module{
    single { Gson() }
    single { ZooRepository(get(),get()) }
    viewModel { IntroVM(get(),get()) }
    viewModel { PlantVM() }
    single { AppDispatcherProvider() as CoroutineDispatcherProvider }
    single { createOkHttpClient() }
    single { createMockWebService<ZooServices>(get()) }

}

fun createOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .retryOnConnectionFailure(true)
        .addNetworkInterceptor(LoggerInterceptor())
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .connectionPool(ConnectionPool(0, 1, TimeUnit.NANOSECONDS))
        .build()
}

inline fun <reified T> createMockWebService(okHttpClient: OkHttpClient): T {
    val url = "https://data.taipei/api/v1/dataset/"
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit.create(T::class.java)
}