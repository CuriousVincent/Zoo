package com.vincentwang.zoo.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainCoroutineDispatcher
import javax.inject.Inject

class AppDispatcherProvider @Inject constructor() : CoroutineDispatcherProvider {
    override fun io(): CoroutineDispatcher = Dispatchers.IO
    override fun ui(): CoroutineDispatcher  = Dispatchers.Main
    override fun default(): CoroutineDispatcher = Dispatchers.Default
    override fun unConfirm(): CoroutineDispatcher = Dispatchers.Unconfined
}