package com.codingwithmitch.daggerhiltplayground.framework.datasource.network

import com.codingwithmitch.daggerhiltplayground.business.domain.models.Blog
import retrofit2.http.GET

interface BlogService {

    @GET("blogs")
    suspend fun get(): List<Blog>
}