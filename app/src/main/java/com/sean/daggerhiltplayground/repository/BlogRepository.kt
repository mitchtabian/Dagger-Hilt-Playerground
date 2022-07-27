package com.sean.daggerhiltplayground.repository

import com.sean.daggerhiltplayground.model.Blog
import com.sean.daggerhiltplayground.retrofit.BlogRetrofit
import com.sean.daggerhiltplayground.retrofit.NetworkMapper
import com.sean.daggerhiltplayground.room.BlogDao
import com.sean.daggerhiltplayground.room.CacheMapper
import com.sean.daggerhiltplayground.utils.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class BlogRepository
constructor(
    private val blogDao: BlogDao,
    private val blogRetrofit: BlogRetrofit,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper
){
    suspend fun getBlog() : Flow<DataState<List<Blog>>> = flow{
        emit(DataState.Loading)
        delay(1000)
        try {
            val networkBlogs = blogRetrofit.getBlogs()
            val blogs = networkMapper.mapFromEntityList(networkBlogs)
            for(blog in blogs){
                blogDao.insert(cacheMapper.mapToEntity(blog))
            }
            val cachedBlogs = blogDao.get()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cachedBlogs)))
        } catch (e: Exception){
            emit(DataState.Error(e))
        }
    }
}