package com.kathayat.imagecaching.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kathayat.imagecaching.network.remote.ImagesDtoItem
import com.kathayat.imagecaching.usecases.GetImagesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(
    private val getImagesUseCases: GetImagesUseCases
): ViewModel() {
    private val _imagesState: MutableStateFlow<PagingData<ImagesDtoItem>> = MutableStateFlow(value = PagingData.empty())
    val imagesState: MutableStateFlow<PagingData<ImagesDtoItem>> get() = _imagesState

    init {
        onEvent(HomeEvent.GetHome)
    }

    fun onEvent(event: HomeEvent) {
        viewModelScope.launch {
            when (event) {
                is HomeEvent.GetHome -> {
                    getImages()
                }
            }
        }
    }

    private suspend fun getImages() {
        getImagesUseCases.execute(Unit)
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .collect {
                _imagesState.value = it
            }
    }
}


sealed class HomeEvent {
    object GetHome : HomeEvent()
}



