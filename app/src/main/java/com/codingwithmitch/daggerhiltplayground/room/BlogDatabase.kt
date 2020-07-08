package com.codingwithmitch.daggerhiltplayground.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.codingwithmitch.daggerhiltplayground.room.BlogCacheEntity
import com.codingwithmitch.daggerhiltplayground.room.BlogDao

@Database(entities = [BlogCacheEntity::class ], version = 1)
abstract class BlogDatabase: RoomDatabase() {

    abstract fun blogDao(): BlogDao

    companion object{
        val DATABASE_NAME: String = "blog_db"
    }


}