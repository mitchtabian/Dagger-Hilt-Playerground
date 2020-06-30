package com.codingwithmitch.daggerhiltplayground.di

import com.codingwithmitch.daggerhiltplayground.business.interactors.GetBlogs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {


}