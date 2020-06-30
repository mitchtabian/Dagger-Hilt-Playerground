package com.codingwithmitch.daggerhiltplayground.business.interactors

import com.codingwithmitch.daggerhiltplayground.business.data.cache.CacheDataSource
import com.codingwithmitch.daggerhiltplayground.business.data.network.NetworkDataSource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GetBlogs
constructor(
    private val cacheDataSource: CacheDataSource,
    private val networkDataSource: NetworkDataSource
){

    fun execute(){
        TODO("Execute the use case")
        // 1. observe cache
        // 2. get data from network
        // 3. insert into cache
    }

}
















