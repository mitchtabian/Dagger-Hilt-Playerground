package com.codingwithmitch.daggerhiltplayground.framework.datasource.network

import com.codingwithmitch.daggerhiltplayground.framework.datasource.network.model.BlogNetworkEntity
import com.codingwithmitch.daggerhiltplayground.framework.datasource.network.retrofit.BlogRetrofit

class BlogRetrofitServiceImpl
constructor(
    private val blogRetrofit: BlogRetrofit
): BlogRetrofitService {

    override suspend fun get(): List<BlogNetworkEntity> {
        return blogRetrofit.get()
    }
}