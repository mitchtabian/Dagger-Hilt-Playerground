package com.codingwithmitch.daggerhiltplayground.framework.datasource.network

import com.codingwithmitch.daggerhiltplayground.framework.datasource.network.model.BlogNetworkEntity

interface BlogRetrofitService {

    suspend fun get(): List<BlogNetworkEntity>
}