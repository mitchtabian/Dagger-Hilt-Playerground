package com.sean.daggerhiltplayground.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.sean.daggerhiltplayground.R
import com.sean.daggerhiltplayground.model.Blog
import com.sean.daggerhiltplayground.utils.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val TAG: String = "AppDebug"

    private val viewModel : BlogViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        subscribeObservers()
        viewModel.setStateEvent(MainStateEvent.GetBlogEvents)
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(this, Observer { dataState ->
            when(dataState){
                is DataState.Error -> {
                    displayProgessBar(false)
                    displayError(dataState.exception.message.orEmpty())
                }
                is DataState.Loading -> {
                    displayProgessBar(true)
                }
                is DataState.Success ->{
                    displayProgessBar(false)
                    addBlogTitlesToText(dataState.data)
                }
            }
        })
    }

    private fun displayError(message: String) {
        if(message != null){
            text.text = message
        } else {
            text.text = "Uknown Error"
        }
    }

    private fun addBlogTitlesToText(blogs: List<Blog>){
        val stringBuilder = StringBuilder()
        for(blog in blogs){
            stringBuilder.append(blog.title + "\n")
        }
        text.text = stringBuilder
    }

    private fun displayProgessBar(isDisplayed: Boolean){
        progress_bar.visibility = if(isDisplayed) View.VISIBLE else View.GONE
    }


}



















