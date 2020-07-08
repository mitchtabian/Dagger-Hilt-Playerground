package com.codingwithmitch.daggerhiltplayground.framework.datasource.network.retrofit

import com.codingwithmitch.daggerhiltplayground.framework.datasource.network.model.BlogNetworkEntity
import retrofit2.http.GET

interface BlogRetrofit {

    @GET("blogs")
    suspend fun get(): List<BlogNetworkEntity>
}