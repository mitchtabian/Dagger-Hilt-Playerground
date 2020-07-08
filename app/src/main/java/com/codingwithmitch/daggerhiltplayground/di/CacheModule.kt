package com.codingwithmitch.daggerhiltplayground.di

import android.content.Context
import androidx.room.Room
import com.codingwithmitch.daggerhiltplayground.business.data.cache.CacheDataSource
import com.codingwithmitch.daggerhiltplayground.business.data.cache.CacheDataSourceImpl
import com.codingwithmitch.daggerhiltplayground.business.domain.models.Blog
import com.codingwithmitch.daggerhiltplayground.business.domain.util.EntityMapper
import com.codingwithmitch.daggerhiltplayground.framework.datasource.cache.database.BlogDao
import com.codingwithmitch.daggerhiltplayground.framework.datasource.cache.database.BlogDatabase
import com.codingwithmitch.daggerhiltplayground.framework.datasource.cache.mappers.CacheMapper
import com.codingwithmitch.daggerhiltplayground.framework.datasource.cache.model.BlogCacheEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object CacheModule {

    @Singleton
    @Provides
    fun provideCacheMapper(): EntityMapper<BlogCacheEntity, Blog>{
        return CacheMapper()
    }

    @Singleton
    @Provides
    fun provideBlogDb(@ApplicationContext context: Context): BlogDatabase {
        return Room
            .databaseBuilder(
                context,
                BlogDatabase::class.java,
                BlogDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideBlogDAO(blogDatabase: BlogDatabase): BlogDao {
        return blogDatabase.blogDao()
    }

    @Singleton
    @Provides
    fun provideCacheDataSource(
        blogDao: BlogDao,
        cacheMapper: CacheMapper
    ): CacheDataSource{
        return CacheDataSourceImpl(blogDao, cacheMapper)
    }


}

























