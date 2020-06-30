package com.codingwithmitch.daggerhiltplayground.business.data.network

import com.codingwithmitch.daggerhiltplayground.business.domain.models.Blog

class NetworkDataSourceImpl : NetworkDataSource{

    override suspend fun get(): List<Blog> {

    }

}