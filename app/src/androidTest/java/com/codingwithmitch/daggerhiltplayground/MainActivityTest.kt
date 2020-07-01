package com.codingwithmitch.daggerhiltplayground

import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.launchFragment
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import com.codingwithmitch.daggerhiltplayground.di.ProductionModule
import com.codingwithmitch.daggerhiltplayground.framework.presentation.MainFragment
import com.codingwithmitch.daggerhiltplayground.util.launchFragmentInHiltContainer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers.containsString
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Singleton

@ExperimentalCoroutinesApi
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
        assertThat(someString, containsString("TEST string"))
    }

//    @Test
//    fun mainFragmentTest(){
//        val scenario = launchFragmentInHiltContainer<MainFragment>(
//            fac
//        )
//        launchFragment(factory = FragmentFactory())
//    }

    @Module
    @InstallIn(ApplicationComponent::class)
    object ProductionModule {


        @Singleton
        @Provides
        fun provideString(): String{
            return "This is a TEST string I'm providing for injection"
        }
    }
}
























