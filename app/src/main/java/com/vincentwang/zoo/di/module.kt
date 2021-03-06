package com.vincentwang.zoo.di

import com.google.gson.Gson
import com.vincentwang.zoo.model.ZooRepository
import com.vincentwang.zoo.model.ZooServices
import com.vincentwang.zoo.ui.intro.IntroVM
import com.vincentwang.zoo.ui.plant.PlantVM
import com.vincentwang.zoo.ui.plant.ResultX
import com.vincentwang.zoo.ui.plant_detail.PlantDetailVM
import com.vincentwang.zoo.util.AppDispatcherProvider
import com.vincentwang.zoo.util.CoroutineDispatcherProvider
import com.vincentwang.zoo.util.LoggerInterceptor
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.Protocol
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

//val zooModule = module{
//    single { Gson() }
//    single { ZooRepository(get(),get()) }
//    viewModel { IntroVM(get(),get()) }
//    viewModel { PlantVM() }
//    viewModel { PlantDetailVM() }
//    single { AppDispatcherProvider() as CoroutineDispatcherProvider }
//    single { createOkHttpClient() }
//    single { createMockWebService<ZooServices>(get()) }
//
//}

@Module
@InstallIn(ApplicationComponent::class)
abstract class AnalyticsModule {

    @Binds
    abstract fun bindCoroutineDispatcherProvider(
        appDispatcherProvider: AppDispatcherProvider
    ): CoroutineDispatcherProvider
}

@Module
@InstallIn(ApplicationComponent::class)
class ApplicationModule {
    @Singleton
    @Provides
    fun provideZooServices(client :OkHttpClient): ZooServices {
        return createMockWebService<ZooServices>(client)
    }
    @Singleton
    @Provides
    fun provideOkHttpClient():OkHttpClient{
        return createOkHttpClient()
    }

    @Provides
    fun provideGson():Gson{
        return Gson()
    }
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