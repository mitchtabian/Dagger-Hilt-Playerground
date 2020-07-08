package com.codingwithmitch.daggerhiltplayground

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject
import javax.inject.Qualifier
import javax.inject.Singleton

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var someClass: SomeClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        println(someClass.doAThing())
    }
}

class SomeClass
@Inject
constructor(
    private val someInterfaceImpl: SomeInterface,
    private val gson: Gson
){
    fun doAThing(): String{
        return "Look I got: ${someInterfaceImpl.getAThing()}"
    }
}

class SomeInterfaceImpl
@Inject
constructor(
    private val someDependency: String
): SomeInterface {
    override fun getAThing() : String{
        return "A Thing, ${someDependency}"
    }
}

interface SomeInterface{
    fun getAThing(): String
}

@InstallIn(ApplicationComponent::class)
@Module
class MyModule{

    @Singleton
    @Provides
    fun provideSomeString(): String{
        return "some string"
    }

    @Singleton
    @Provides
    fun provideSomeInterface(
        someString: String
    ): SomeInterface{
        return SomeInterfaceImpl(someString)
    }

    @Singleton
    @Provides
    fun provideGson(): Gson{
        return Gson()
    }

}





























