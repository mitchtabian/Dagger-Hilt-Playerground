package com.codingwithmitch.daggerhiltplayground.business.data.cache

import com.codingwithmitch.daggerhiltplayground.business.domain.models.Blog
import com.codingwithmitch.daggerhiltplayground.framework.datasource.cache.database.BlogDao
import com.codingwithmitch.daggerhiltplayground.framework.datasource.cache.mappers.CacheMapper
import javax.inject.Inject

class CacheDataSourceImpl
@Inject
constructor(
    private val blogDao: BlogDao,
    private val cacheMapper: CacheMapper
): CacheDataSource{

    override suspend fun insert(blog: Blog): Long {
        return blogDao.insert(cacheMapper.mapToEntity(blog))
    }

    override suspend fun insertList(blogs: List<Blog>) {
        for(blog in blogs){
            blogDao.insert(cacheMapper.mapToEntity(blog))
        }
    }

    override suspend fun get(): List<Blog> {
        return cacheMapper.mapFromEntityList(blogDao.get())
    }

}
