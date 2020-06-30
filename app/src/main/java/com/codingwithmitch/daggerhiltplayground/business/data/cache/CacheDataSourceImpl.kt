package com.codingwithmitch.daggerhiltplayground.business.data.cache

import com.codingwithmitch.daggerhiltplayground.business.domain.models.Blog

class CacheDataSourceImpl : CacheDataSource{

    override suspend fun insert(blog: Blog): Long {

    }

    override suspend fun insertList(blogs: List<Blog>) {

    }

    override suspend fun get(): List<Blog> {

    }

}
