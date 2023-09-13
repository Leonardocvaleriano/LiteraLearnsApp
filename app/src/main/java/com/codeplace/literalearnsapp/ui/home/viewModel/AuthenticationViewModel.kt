package com.codeplace.literalearnsapp.ui.home.viewModel

import androidx.lifecycle.MutableLiveData
import com.codeplace.literalearnsapp.repository.LiteraLearnsRepository
import com.codeplace.literalearnsapp.stateFlow.StateFlow
import com.codeplace.literalearnsapp.ui.baseViewModel.BaseViewModel

class AuthenticationViewModel(private val literaLearnsRepository: LiteraLearnsRepository): BaseViewModel() {

    val tokenAuthenticated = MutableLiveData<StateFlow>()

    fun getTokenAuthenticated(
        clientId:String,
        clientSecret:String,
        authCode: String?,
        grantType:String,
        redirectUri:String) = fetchData(tokenAuthenticated) {
        literaLearnsRepository.getTokenAuthenticated(clientId,clientSecret,authCode,grantType,redirectUri)
    }
}