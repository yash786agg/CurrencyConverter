package com.app.currencyConverter.datasource.remote

import com.app.currencyConverter.commons.util.Constants.Companion.TIMEOUT_REQUEST
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

fun createNetworkClient(baseUrl: String) = retrofitClient(baseUrl, httpClient())

private fun httpClient(): OkHttpClient =
    OkHttpClient.Builder()
        .connectTimeout(TIMEOUT_REQUEST, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT_REQUEST, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .writeTimeout(TIMEOUT_REQUEST, TimeUnit.SECONDS).build()

private fun retrofitClient(baseUrl: String, httpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(httpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
