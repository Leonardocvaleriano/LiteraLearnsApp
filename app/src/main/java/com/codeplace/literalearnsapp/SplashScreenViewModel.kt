package com.codeplace.literalearnsapp

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenViewModel:ViewModel() {

     var isLoading = mutableStateOf(true)
         private set

    init {
        loadPage()
    }

    private fun loadPage() {
        viewModelScope.launch {
            delay(3000)
            isLoading.value = false
        }
    }
}
