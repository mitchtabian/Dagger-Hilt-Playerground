package com.codingwithmitch.daggerhiltplayground

import androidx.test.espresso.matcher.ViewMatchers.assertThat
import com.codingwithmitch.daggerhiltplayground.di.ProductionModule
import com.codingwithmitch.daggerhiltplayground.framework.presentation.MainFragment
import com.codingwithmitch.daggerhiltplayground.framework.presentation.MainFragmentFactory
import com.codingwithmitch.daggerhiltplayground.util.launchFragmentInHiltContainer
import dagger.Module
import dagger.Provides
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers.containsString
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@HiltAndroidTest
class MainActivityTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var someString: String

    @Inject
    lateinit var fragmentFactory: MainFragmentFactory

//    @BindValue var myString: String = "gggf" // Doesn't work?? I'm prob doing it wrong.

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun hiltTest(){
        assertThat(someString, containsString("TEST string"))
    }

    @Test
    fun mainFragmentTest(){
        val scenario = launchFragmentInHiltContainer<MainFragment>(
            factory = fragmentFactory
        )
    }

}


@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [ProductionModule::class]
)
object FakeProductionModule {

    @Singleton
    @Provides
    fun provideString(): String{
        return "This is a TEST string I'm providing for injection"
    }
}























