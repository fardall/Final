package com.finalproject.data.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    fun <S> createService(serviceClass: Class<S>, okhttpClient: OkHttpClient): S {
        val gson = GsonBuilder().create()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okhttpClient)
            .build()

        return retrofit.create(serviceClass)
    }
}