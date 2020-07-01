package com.codingwithmitch.daggerhiltplayground

import androidx.test.espresso.matcher.ViewMatchers.assertThat
import com.codingwithmitch.daggerhiltplayground.di.ProductionModule
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.hamcrest.CoreMatchers.containsString
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@UninstallModules(ProductionModule::class)
@HiltAndroidTest
class MainActivityTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var someString: String

//    @BindValue var myString: String = "gggf" // Doesn't work??

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun hiltTest(){
        println("MainActivityTest: ${someString}")
        assertThat(someString, containsString("TEST string"))
    }

//    @Module
//    @InstallIn(ApplicationComponent::class)
//    object ProductionModule {
//
//
//        @Singleton
//        @Provides
//        fun provideString(): String{
//            return "This is a TEST string I'm providing for injection"
//        }
//    }
}
























