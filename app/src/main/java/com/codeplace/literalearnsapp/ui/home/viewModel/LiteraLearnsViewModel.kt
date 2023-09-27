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


    val API_KEY = BuildConfig.API_KEY

    val volumeIdWantToRead = 2
    val volumeIdReadingNow = 3
    val volumeIdRead = 4


    val tokenAuthenticated = MutableLiveData<StateFlow>()
    val readingNowShelf = MutableLiveData<StateFlow>()
    val wantToReadShelf = MutableLiveData<StateFlow>()
    val readShelf = MutableLiveData<StateFlow>()

    val coverBookReadingNowList = ArrayList<ShelvesContent>()
    val coverBookWantToReadList = ArrayList<ShelvesContent>()
    val coverBookReadList = ArrayList<ShelvesContent>()

    // val allShelvesResults = mutableListOf<ShelvesResults>()

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
        getWantToReadShelf(volumeIdWantToRead, API_KEY, "$tokenType $accessToken")
        getReadShelf(volumeIdRead,API_KEY, "$tokenType $accessToken")
    }

    fun getReadingNowShelf(volumeId:Int,apiKey: String, accessToken:String)=fetchData(readingNowShelf){
        literaLearnsRepository.getBookShelves(volumeId,apiKey, accessToken)
    }
    fun getWantToReadShelf(volumeId: Int, apiKey: String, accessToken: String)=fetchData(wantToReadShelf) {
        literaLearnsRepository.getBookShelves(volumeId,apiKey, accessToken)
    }
    fun getReadShelf(volumeId: Int, apiKey: String, accessToken: String)=fetchData(wantToReadShelf) {
        literaLearnsRepository.getBookShelves(volumeId,apiKey, accessToken)
    }

    fun fillReadingNowShelfList(result: JSONObject) {
        val resultJSONArray = result.getJSONArray("items")
        var indexJsonObject = 0
        (0 until resultJSONArray.length())
            .map {
                resultJSONArray
                .getJSONObject(indexJsonObject++)
                .getJSONObject("volumeInfo")
                .getJSONObject("imageLinks")
                .getString("thumbnail")
            }.forEach {
                 coverBookReadingNowList.add(ShelvesContent(it))
            }
        allShelvesResult.postValue(ShelvesResults(coverBookReadingNowList = coverBookReadingNowList))
    }


    fun fillWantToReadShelf(result: JSONObject){
        val getJSONObject = result.getJSONObject("items")
        val resultJSONArray = result.getJSONArray("items")
        var indexJsonObject = 0
        (0 until resultJSONArray.length())
            .map {
                resultJSONArray
                    .getJSONObject(indexJsonObject++)
                    .getJSONObject("volumeInfo")
                    .getJSONObject("imageLinks")
                    .getString("thumbnail")
            }.forEach {
                coverBookWantToReadList.add(ShelvesContent(it))
            }
        allShelvesResult.postValue(ShelvesResults(coverBookWantToReadList = coverBookWantToReadList))

    }
    fun fillReadShelf(result: JSONObject){

        val resultJSONArray = result.getJSONArray("items")
        var indexJsonObject = 0
        (0 until resultJSONArray.length())
            .map {
                resultJSONArray
                    .getJSONObject(indexJsonObject++)
                    .getJSONObject("volumeInfo")
                    .getJSONObject("imageLinks")
                    .getString("thumbnail")
            }.forEach {
                coverBookReadList.add(ShelvesContent(it))
            }
        allShelvesResult.postValue(ShelvesResults(coverBookReadList = coverBookReadList))

    }

}