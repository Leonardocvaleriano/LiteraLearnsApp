package com.codeplace.literalearnsapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

import androidx.lifecycle.ViewModel

class AppNavigationViewModel:ViewModel() {

//    var selectedBottomBarItemIndex  rememberSaveable {
//        mutableStateOf(0)
//    }

    var selectedBottomItemIndex by mutableStateOf(0)
        private set

//


    fun updateSelectedBottomItemIndex (itemIndex:Int){
        selectedBottomItemIndex = itemIndex
    }

}