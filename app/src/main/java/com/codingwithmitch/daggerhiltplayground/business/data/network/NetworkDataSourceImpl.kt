package com.codingwithmitch.daggerhiltplayground.business.data.network

import com.codingwithmitch.daggerhiltplayground.business.domain.models.Blog
import com.codingwithmitch.daggerhiltplayground.framework.datasource.network.BlogService
import javax.inject.Inject

class NetworkDataSourceImpl
@Inject
constructor(
    private val blogService: BlogService
): NetworkDataSource{

    override suspend fun get(): List<Blog> {

    }

}