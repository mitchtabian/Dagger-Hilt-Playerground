package com.codingwithmitch.daggerhiltplayground.framework.datasource.cache

import com.codingwithmitch.daggerhiltplayground.framework.datasource.cache.model.BlogCacheEntity

interface BlogDaoService {

    suspend fun insert(blogEntity: BlogCacheEntity): Long

    suspend fun get(): List<BlogCacheEntity>

}