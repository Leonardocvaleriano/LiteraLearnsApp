package com.codeplace.literalearnsapp.ui.home.viewModel

import androidx.lifecycle.MutableLiveData
import com.codeplace.literalearnsapp.BuildConfig
import com.codeplace.literalearnsapp.repository.LiteraLearnsRepository
import com.codeplace.literalearnsapp.stateFlow.StateFlow
import com.codeplace.literalearnsapp.ui.baseViewModel.BaseViewModel
import com.codeplace.literalearnsapp.ui.home.view.model.ShelvesContent
import com.codeplace.literalearnsapp.ui.home.view.model.ShelvesResults
import org.json.JSONObject

class LiteraLearnsViewModel(private val literaLearnsRepository: LiteraLearnsRepository): BaseViewModel() {


    val tokenAuthenticated = MutableLiveData<StateFlow>()
    val readingNowShelf = MutableLiveData<StateFlow>()
    val wantToReadShelf = MutableLiveData<StateFlow>()
    val readShelf = MutableLiveData<StateFlow>()

    val coverBookReadingNowList = ArrayList<ShelvesContent>()
    val coverBookWantToReadList = ArrayList<ShelvesContent>()
    val coverBookReadList = ArrayList<ShelvesContent>()



    // val allShelvesResults = mutableListOf<ShelvesResults>()


    val API_KEY = BuildConfig.API_KEY
    val volumeIdReadingNow = 3

    val allShelvesResult = MutableLiveData<ShelvesResults>()

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

    fun fillReadingNowShelfList(result: JSONObject) {
        val resultJSONArray = result.getJSONArray("items")
        (0 until resultJSONArray.length())
            .map { resultJSONArray
                .getJSONObject(0)
                .getJSONObject("volumeInfo")
                .getJSONObject("imageLinks")
                .getString("smallThumbnail")
            }
            .forEach {
                coverBookReadingNowList.add(ShelvesContent(it))
            }
        allShelvesResult.postValue(ShelvesResults(coverBookReadingNowList = coverBookReadingNowList))
    }

    fun fillWantToReadShelf(
        result: JSONObject){

       // allShelvesResult.postValue(ShelvesResults())

    }
    fun fillReadShelf(result: JSONObject){

        //allShelvesResult.postValue(ShelvesResults(totalItemsWantToRead = totalItems))
    }


}