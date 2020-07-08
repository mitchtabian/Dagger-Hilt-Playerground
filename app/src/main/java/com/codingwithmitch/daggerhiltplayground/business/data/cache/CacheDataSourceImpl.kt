package com.codingwithmitch.daggerhiltplayground.business.data.cache

import com.codingwithmitch.daggerhiltplayground.business.domain.models.Blog
import com.codingwithmitch.daggerhiltplayground.framework.datasource.cache.BlogDaoService
import com.codingwithmitch.daggerhiltplayground.framework.datasource.cache.mappers.CacheMapper

class CacheDataSourceImpl
constructor(
    private val blogDaoService: BlogDaoService,
    private val cacheMapper: CacheMapper
): CacheDataSource{

    override suspend fun insert(blog: Blog): Long {
        return blogDaoService.insert(cacheMapper.mapToEntity(blog))
    }

    override suspend fun insertList(blogs: List<Blog>){
        for(blog in blogs) {
            blogDaoService.insert(cacheMapper.mapToEntity(blog))
        }
    }

    override suspend fun get(): List<Blog> {
        return cacheMapper.mapFromEntityList(blogDaoService.get())
    }

}
