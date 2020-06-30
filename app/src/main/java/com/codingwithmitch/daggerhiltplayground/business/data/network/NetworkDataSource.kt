package com.codingwithmitch.daggerhiltplayground.business.data.network

import com.codingwithmitch.daggerhiltplayground.business.domain.models.Blog

interface NetworkDataSource {

    suspend fun get(): List<Blog>
}