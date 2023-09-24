package com.codeplace.literalearnsapp.ui.home.viewModel

import androidx.lifecycle.MutableLiveData
import com.codeplace.literalearnsapp.BuildConfig
import com.codeplace.literalearnsapp.repository.LiteraLearnsRepository
import com.codeplace.literalearnsapp.stateFlow.StateFlow
import com.codeplace.literalearnsapp.ui.baseViewModel.BaseViewModel
import com.codeplace.literalearnsapp.ui.home.view.model.ShelvesResults
import org.json.JSONObject

class LiteraLearnsViewModel(private val literaLearnsRepository: LiteraLearnsRepository): BaseViewModel() {


    val tokenAuthenticated = MutableLiveData<StateFlow>()
    val readingNowShelf = MutableLiveData<StateFlow>()
    val wantToReadShelf = MutableLiveData<StateFlow>()
    val readShelf = MutableLiveData<StateFlow>()


   // val allShelvesResults = mutableListOf<ShelvesResults>()


    val API_KEY = BuildConfig.API_KEY
    val volumeIdReadingNow = 3

    val allShelvesResult = MutableLiveData<ShelvesResults>()

    var totalItems = 0

    fun getTokenAuthenticated(
        clientId:String,
        clientSecret:String,
        authCode: String?,
        grantType:String,
        redirectUri:String) = fetchData(tokenAuthenticated) {
        literaLearnsRepository.getTokenAuthenticated(clientId,clientSecret,authCode,grantType,redirectUri)
    }

    fun getBookShelves(result: JSONObject) {
        val accessToken = result.getString("access_token")
        val tokenType = result.getString("token_type")
        getReadingNowShelf(volumeIdReadingNow,API_KEY, "$tokenType $accessToken")
        getWantToReadShelf()
        getReadShelf()
    }

    fun getReadingNowShelf(volumeId:Int,apiKey: String, accessToken:String)= fetchData(readingNowShelf){
        literaLearnsRepository.getBookShelves(volumeId,apiKey, accessToken)
    }
    fun getWantToReadShelf(){

    }
    fun getReadShelf(){

    }


    fun fillReadingNowShelf(result: JSONObject){
        val totalItems =  result.getInt("totalItems")
        val coverImage = result.getJSONArray("items").getJSONObject(0).getJSONObject("volumeInfo").getJSONObject("imageLinks").getString("smallThumbnail")
        allShelvesResult.postValue(ShelvesResults(
            totalItemsReadingNow = totalItems,
            coverImageReadingNow = coverImage ))
    }

    fun fillWantToReadShelf(result: JSONObject){
        val totalItems = ""
        val coverImage = ""
        //allShelvesResult.postValue(ShelvesResults(totalItemsWantToRead = totalItems))

    }
    fun fillReadShelf(result: JSONObject){
        val totalItems = ""
        val coverImage = ""
        //allShelvesResult.postValue(ShelvesResults(totalItemsWantToRead = totalItems))
    }


}