package com.codingwithmitch.daggerhiltplayground.business.data.network

import com.codingwithmitch.daggerhiltplayground.business.domain.models.Blog
import com.codingwithmitch.daggerhiltplayground.framework.datasource.network.BlogRetrofitService
import com.codingwithmitch.daggerhiltplayground.framework.datasource.network.mappers.NetworkMapper

class NetworkDataSourceImpl
constructor(
    private val blogRetrofitService: BlogRetrofitService,
    private val networkMapper: NetworkMapper
): NetworkDataSource{

    override suspend fun get(): List<Blog> {
        return networkMapper.mapFromEntityList(blogRetrofitService.get())
    }

}