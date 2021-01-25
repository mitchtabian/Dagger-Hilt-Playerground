package com.codingwithmitch.daggerhiltplayground.di

import androidx.fragment.app.FragmentFactory
import com.codingwithmitch.daggerhiltplayground.framework.presentation.MainFragmentFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
object FragmentModule {

    @Singleton
    @Provides
    fun provideMainFragmentFactory(someString: String): FragmentFactory {
        return MainFragmentFactory(someString)
    }
}





