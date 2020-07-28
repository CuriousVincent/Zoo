package com.vincentwang.zoo.di

import com.google.gson.Gson
import com.vincentwang.zoo.model.ZooRepository
import org.koin.dsl.module

val zooModule = module{
    single { Gson() }
    single { ZooRepository(get()) }
}