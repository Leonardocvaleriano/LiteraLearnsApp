package com.codeplace.literalearnsapp.repository

import com.codeplace.literalearnsapp.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LiteraLearnsRepository(private val baseUrlToken: String, private val baseUrlGoogle:String) {


    suspend fun getTokenAuthenticated(
        clientId:String,
        clientSecret:String,
        authCode: String?,
        grantType:String,
        redirectUri:String) = withContext(Dispatchers.IO){
        val api = RetrofitInstance.getRetrofit(baseUrlToken)
        return@withContext api.getTokenAuthenticated(clientId,clientSecret,authCode,grantType,redirectUri)
    }
    suspend fun getBookShelves(
        volumeId:Int,
        apiKey:String,
        accessToken:String) = withContext(Dispatchers.IO){
        val api = RetrofitInstance.getRetrofit(baseUrlGoogle)
        return@withContext api.getBookShelves( apiKey = apiKey, volumeId = volumeId, accessToken = accessToken)
    }
}