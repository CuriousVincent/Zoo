package com.vincentwang.zoo.di

import com.google.gson.Gson
import com.vincentwang.zoo.model.ZooRepository
import com.vincentwang.zoo.ui.intro.IntroVM
import com.vincentwang.zoo.util.AppDispatcherProvider
import com.vincentwang.zoo.util.CoroutineDispatcherProvider
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val zooModule = module{
    single { Gson() }
    single { ZooRepository(get()) }
    viewModel { IntroVM(get(),get()) }
    single { AppDispatcherProvider() as CoroutineDispatcherProvider }
}