package com.codeplace.literalearnsapp.ui.home.viewModel

import androidx.lifecycle.MutableLiveData
import com.codeplace.literalearnsapp.BuildConfig
import com.codeplace.literalearnsapp.repository.LiteraLearnsRepository
import com.codeplace.literalearnsapp.stateFlow.StateFlow
import com.codeplace.literalearnsapp.ui.baseViewModel.BaseViewModel
import org.json.JSONObject

class AuthenticationViewModel(private val literaLearnsRepository: LiteraLearnsRepository): BaseViewModel() {


    val tokenAuthenticated = MutableLiveData<StateFlow>()
    val readingNowBooks = MutableLiveData<StateFlow>()
    val API_KEY = BuildConfig.API_KEY
    val volumeIdReadingNow = 3

    fun getTokenAuthenticated(
        clientId:String,
        clientSecret:String,
        authCode: String?,
        grantType:String,
        redirectUri:String) = fetchData(tokenAuthenticated) {
        literaLearnsRepository.getTokenAuthenticated(clientId,clientSecret,authCode,grantType,redirectUri)
    }

    fun getBookShelves(result: JSONObject) {
        val accessToken = result.getString("access_token").toString()
        getReadingNowShelf(volumeIdReadingNow,API_KEY, "Bearer $accessToken")
    }

    fun getReadingNowShelf(volumeId:Int,apiKey: String, accessToken:String)= fetchData(readingNowBooks){
        literaLearnsRepository.getBookShelves(volumeId,apiKey, accessToken)
    }
}