package com.codeplace.literalearnsapp.ui.home.viewModel

import androidx.lifecycle.MutableLiveData
import com.codeplace.literalearnsapp.repository.LiteraLearnsRepository
import com.codeplace.literalearnsapp.stateFlow.StateFlow
import com.codeplace.literalearnsapp.ui.baseViewModel.BaseViewModel

class AuthenticationViewModel(private val literaLearnsRepository: LiteraLearnsRepository): BaseViewModel() {

    val accountVallues = MutableLiveData<StateFlow>()

}