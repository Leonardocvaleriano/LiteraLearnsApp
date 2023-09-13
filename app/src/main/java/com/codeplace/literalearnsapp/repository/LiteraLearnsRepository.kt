package com.codeplace.literalearnsapp.repository

import com.codeplace.literalearnsapp.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.collections.ArrayList

class LiteraLearnsRepository(private val baseUrl: String) {
    suspend fun getTokenAuthenticated(
        clientId:String,
        clientSecret:String,
        authCode: String?,
        grantType:String,
        redirectUri:String) = withContext(Dispatchers.IO){
        val api = RetrofitInstance.getRetrofit(baseUrl)
        return@withContext api.getTokenAuthenticated(clientId,clientSecret,authCode,grantType,redirectUri)
    }
}