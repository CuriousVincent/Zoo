package com.vincentwang.zoo.util

import com.orhanobut.logger.Logger
import okhttp3.Interceptor
import okhttp3.Response

class LoggerInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        Logger.wtf(String.format("[API Request] %s %n%s%n%s",
            request.url, chain.connection(), request.headers
        ))
        return chain.proceed(request)
    }
}