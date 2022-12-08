package com.jit.quotes.api

import com.jit.quotes.data.Quotes
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.quotable.io/"

val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface QuotesApiService{
    @GET("quotes")
    suspend fun getQuotes():Quotes
}

object QuotesApi{
    val retrofitService:QuotesApiService by lazy{
        retrofit.create(QuotesApiService::class.java)
    }
}

