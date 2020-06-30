package com.codingwithmitch.daggerhiltplayground.framework.presentation

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.codingwithmitch.daggerhiltplayground.business.interactors.GetBlogs

class MainViewModel
@ViewModelInject
constructor(
    private val mainRepository: GetBlogs,
    @Assisted private val savedStateHandle: SavedStateHandle
): ViewModel() {


}



















