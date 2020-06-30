package com.codingwithmitch.daggerhiltplayground.business.data.cache

import com.codingwithmitch.daggerhiltplayground.business.domain.models.Blog

interface CacheDataSource {

    suspend fun insert(blog: Blog): Long

    suspend fun insertList(blogs: List<Blog>)

    suspend fun get(): List<Blog>
}