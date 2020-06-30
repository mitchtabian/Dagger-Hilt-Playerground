package com.codingwithmitch.daggerhiltplayground.framework.datasource.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.codingwithmitch.daggerhiltplayground.framework.datasource.cache.model.BlogCacheEntity

@Database(entities = [BlogCacheEntity::class ], version = 1)
abstract class BlogDatabase: RoomDatabase() {

    abstract fun blogDao(): BlogDao

    companion object{
        val DATABASE_NAME: String = "blog_db"
    }


}