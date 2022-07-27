package com.sean.daggerhiltplayground.di

import com.sean.daggerhiltplayground.repository.BlogRepository
import com.sean.daggerhiltplayground.retrofit.BlogRetrofit
import com.sean.daggerhiltplayground.retrofit.NetworkMapper
import com.sean.daggerhiltplayground.room.BlogDao
import com.sean.daggerhiltplayground.room.CacheMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        blogDao: BlogDao,
        retrofit: BlogRetrofit,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper
    ) : BlogRepository{
        return BlogRepository(blogDao, retrofit, cacheMapper, networkMapper)
    }
}