package com.finalproject.data.api

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

internal object OkHttpClientService {
    private const val DEFAULT_MAX_REQUEST = 30

    fun create(context: Context): OkHttpClient {

        val builder = OkHttpClient.Builder()
            .addInterceptor(ChuckerInterceptor(context))
            .readTimeout(6000, TimeUnit.SECONDS)
            .connectTimeout(6000, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)

        val dispatcher = Dispatcher()
        dispatcher.maxRequests = DEFAULT_MAX_REQUEST
        builder.dispatcher(dispatcher)

        return builder.build()
    }
}