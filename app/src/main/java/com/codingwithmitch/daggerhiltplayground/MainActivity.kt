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

        println(someClass.doAThing1())
        println(someClass.doAThing2())
    }
}

class SomeClass
@Inject
constructor(
    @Impl1 private val someInterfaceImpl1: SomeInterface,
    @Impl2 private val someInterfaceImpl2: SomeInterface
){
    fun doAThing1(): String{
        return "Look I got: ${someInterfaceImpl1.getAThing()}"
    }

    fun doAThing2(): String{
        return "Look I got: ${someInterfaceImpl2.getAThing()}"
    }
}

class SomeInterfaceImpl1
@Inject
constructor(): SomeInterface {
    override fun getAThing() : String{
        return "A Thing1"
    }
}

class SomeInterfaceImpl2
@Inject
constructor(): SomeInterface {
    override fun getAThing() : String{
        return "A Thing2"
    }
}

interface SomeInterface{
    fun getAThing(): String
}

@InstallIn(ApplicationComponent::class)
@Module
class MyModule{

    @Impl1
    @Singleton
    @Provides
    fun provideSomeInterface1(): SomeInterface{
        return SomeInterfaceImpl1()
    }

    @Impl2
    @Singleton
    @Provides
    fun provideSomeInterface2(): SomeInterface{
        return SomeInterfaceImpl2()
    }
}


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Impl1

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Impl2




























