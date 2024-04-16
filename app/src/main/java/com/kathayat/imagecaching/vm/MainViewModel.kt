package com.kathayat.imagecaching.vm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.kathayat.imagecaching.ui.theme.AppTheme
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

//@HiltViewModel
//class MainViewModel @Inject constructor() : ViewModel() {
//
//    var stateApp by mutableStateOf(MainState())
//
//    fun onEvent(event: MainEvent) {
//        when(event) {
//            is MainEvent.ThemeChange -> {
//                stateApp = stateApp.copy(theme = event.theme)
//            }
//        }
//    }
//
//}
//
//sealed class MainEvent {
//    data class ThemeChange(val theme: AppTheme): MainEvent()
//}
//
//data class MainState(
//    val theme: AppTheme = AppTheme.Default,
//)

