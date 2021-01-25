package com.codingwithmitch.daggerhiltplayground.framework.presentation

import androidx.hilt.Assisted
import androidx.lifecycle.*
import com.codingwithmitch.daggerhiltplayground.business.domain.models.Blog
import com.codingwithmitch.daggerhiltplayground.business.domain.state.DataState
import com.codingwithmitch.daggerhiltplayground.business.interactors.GetBlogs
import com.codingwithmitch.daggerhiltplayground.framework.presentation.MainStateEvent.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class MainViewModel
@Inject
constructor(
    private val getBlogs: GetBlogs,
    @Assisted private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _dataState: MutableLiveData<DataState<List<Blog>>> = MutableLiveData()

    val dataState: LiveData<DataState<List<Blog>>>
        get() = _dataState

    fun setStateEvent(mainStateEvent: MainStateEvent){
        viewModelScope.launch {
            when(mainStateEvent){
                is GetBlogsEvent -> {
                    getBlogs.execute()
                        .onEach {dataState ->
                            _dataState.value = dataState
                        }
                        .launchIn(viewModelScope)
                }
            }
        }
    }

}


sealed class MainStateEvent{

    object GetBlogsEvent: MainStateEvent()

    object None: MainStateEvent()
}


















