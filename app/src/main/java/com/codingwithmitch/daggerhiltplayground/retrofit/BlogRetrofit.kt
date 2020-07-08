package com.codingwithmitch.daggerhiltplayground.retrofit

import com.codingwithmitch.daggerhiltplayground.retrofit.BlogNetworkEntity
import retrofit2.http.GET

interface BlogRetrofit {

    @GET("blogs")
    suspend fun get(): List<BlogNetworkEntity>
}