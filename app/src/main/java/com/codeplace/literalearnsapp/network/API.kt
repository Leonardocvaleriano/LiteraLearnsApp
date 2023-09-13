package com.codeplace.literalearnsapp.network

import retrofit2.Response
import retrofit2.http.*
import kotlin.collections.ArrayList

interface API {


    // Get a specific bookshelve
    //https://www.googleapis.com/books/v1/mylibrary/bookshelves/0/volumes?&key=AIzaSyA7QniVf2iaS8U100eVZX-24TlmrcCSJHM

    //exchangeAuthorizationCodeForToken
    @FormUrlEncoded
    @POST("token")
    suspend fun getTokenAuthenticated(
        @Field("clientId") clientId:String,
        @Field("clientSecret") clientSecret:String,
        @Field("code") authCode: String?,
        @Field("grant_type") grantType:String,
        @Field("redirectUri") redirectUri:String):Response<Map<Any, *>>


//    @GET("mylibrary/bookshelves")
//    suspend fun getBookShelves():Response<Map<String, *>>



}