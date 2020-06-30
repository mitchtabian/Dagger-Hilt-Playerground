package com.codingwithmitch.daggerhiltplayground.business.data.network

import com.codingwithmitch.daggerhiltplayground.business.domain.models.Blog
import com.codingwithmitch.daggerhiltplayground.framework.datasource.network.BlogService
import com.codingwithmitch.daggerhiltplayground.framework.datasource.network.mappers.NetworkMapper
import javax.inject.Inject

class NetworkDataSourceImpl
@Inject
constructor(
    private val blogService: BlogService,
    private val networkMapper: NetworkMapper
): NetworkDataSource{

    override suspend fun get(): List<Blog> {
        return networkMapper.mapFromEntityList(blogService.get())
    }

}