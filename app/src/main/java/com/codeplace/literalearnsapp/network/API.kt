package com.codeplace.literalearnsapp.network

import retrofit2.Response
import retrofit2.http.*

interface API {

    // geting all the books in my list
// GET https://www.googleapis.com/books/v1/mylibrary/bookshelves?key=yourAPIKey Authorization: /* auth token here */

//https://oauth2.googleapis.com/token

    @POST("oauth2/token")
    suspend fun exchangeAuthorizationCodeForToken():Response<Map<String, *>>



    @GET("volumes/{volumeId}")
    suspend fun getBooksVolume(
        @Path("volumeId")volumeId:Int):Response<Map<String, *>>

}