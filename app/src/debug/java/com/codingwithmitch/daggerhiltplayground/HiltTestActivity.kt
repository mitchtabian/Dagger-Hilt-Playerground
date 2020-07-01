package com.codingwithmitch.daggerhiltplayground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.codingwithmitch.daggerhiltplayground.framework.presentation.MainFragmentFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class HiltTestActivity : AppCompatActivity(){

    @Inject
    lateinit var fragmentFactory: MainFragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.fragmentFactory = fragmentFactory
    }
}